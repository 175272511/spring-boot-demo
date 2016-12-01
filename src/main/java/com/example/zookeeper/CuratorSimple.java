package com.example.zookeeper;

import com.example.designmode.company.adapter.section2.Client;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.framework.recipes.barriers.DistributedBarrier;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.locks.*;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuhui on 2016/12/1.
 */
public class CuratorSimple {

    public static void main(String[] args) throws Exception {

        final String path = "/curator-test";
//        CURATOR 调用说明
//        创建并监听变化
        final CuratorFramework curatorFramework = CuratorFrameworkFactory
                .builder()
                .connectString("192.168.1.205:2181")
//                重试策略
//                <> RetryOneTime: 只重连一次.
//                <> RetryNTime: 指定重连的次数N.
//                <> RetryUtilElapsed: 指定最大重连超时时间和重连时间间隔,间歇性重连直到超时或者链接成功.
//                <> ExponentialBackoffRetry: 基于"backoff"方式重连,和RetryUtilElapsed的区别是重连的时间间隔是动态的.
//                时间间隔 = baseSleepTimeMs * Math.max(1, random.nextInt(1 << (retryCount + 1))).
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        curatorFramework.start();
        Stat stat = curatorFramework.checkExists().forPath(path);
        if (stat == null){
            curatorFramework.create().forPath(path, "123".getBytes());
        }
        final NodeCache nodeCache = new NodeCache(curatorFramework, path);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            //节点监听
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("节点变更" + new String(nodeCache.getCurrentData().getData()));
            }
        });
        nodeCache.start();


        /**选举机制*/
        //在分布式调度中可以采用选举机制防止任务被重复执行
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();

                        new LeaderSelector(curatorFramework, path, new LeaderSelectorListener() {
                            //获取master
                            @Override
                            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                                System.out.println(Thread.currentThread().getName() + "成为master");
                                Thread.sleep(2000);
                                System.out.println("完成操作,释放master权限");
                            }

                            @Override
                            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                                //暂停(SUSPENDED): 当连接丢失, 将暂停所有操作, 直到连接重新建立, 如果在规定时间内无法建立连接, 将触发LOST通知
                                //重连(RECONNECTED): 连接丢失, 执行重连时, 将触发该通知
                                //丢失(LOST): 连接超时时, 将触发该通知
                                System.out.println("当前状态:" + connectionState);
                                if (!connectionState.isConnected()){
                                    System.out.println("会话挂了");
                                }

                            }
                        }).start();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        countDownLatch.countDown();


        /**分布式锁*/
//        可重入锁:意味着同一个客户端在拥有锁的同时，可以多次获取，不会被阻塞。你可以在一个线程中多次调用acquire,在线程拥有锁时它总是返回true。
        InterProcessMutex lock = new InterProcessMutex(curatorFramework, path);
//        不可重入锁Shared Lock,这个锁和上面的相比，就是少了Reentrant的功能，也就意味着它不能在同一个线程中重入。
//        InterProcessSemaphoreMutex
//        可重入读写锁Shared Reentrant Read Write Lock
//        new InterProcessReadWriteLock(curatorFramework,path).readLock();
//        new InterProcessReadWriteLock(curatorFramework,path).writeLock();
//        信号量Shared Semaphore
//        new InterProcessSemaphoreV2(curatorFramework, path, 10);
//        多锁对象 Multi Shared Lock Multi Shared Lock是一个锁的容器。 当调用acquire， 所有的锁都会被acquire，如果请求失败，
//        所有的锁都会被release。 同样调用release时所有的锁都被release(失败被忽略)。 基本上，它就是组锁的代表，在它上面的请求释放操作都会传递给它包含的所有的锁。
//        new InterProcessMultiLock(curatorFramework, null);


        //加锁
        lock.acquire();
        //提供超时机制
//        lock.acquire(10, TimeUnit.SECONDS);
        //将锁设为可撤销的. 当别的进程或线程想让你释放锁时Listener会被调用。
//        lock.makeRevocable(new RevocationListener<InterProcessMutex>() {
//            @Override
//            public void revocationRequested(InterProcessMutex interProcessMutex) {
//
//            }
//        });
        //请求撤销锁
//        Revoker.attemptRevoke(curatorFramework, path);
        //释放锁
        lock.release();


        /**分布式计数器*/
        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(curatorFramework, path, new RetryNTimes(3, 1000));
        AtomicValue<Integer> add = atomicInteger.add(8);
        System.out.println(add.succeeded());

        /**分布式Barrier*/
        //用来控制多线程同步, 类似java中的CyclicBarrier, 也就是CountDownLatch的可循环使用版本
        DistributedBarrier barrier = new DistributedBarrier(curatorFramework, path);
        //设置栅栏
        barrier.setBarrier();
        //等待栅栏释放
        barrier.waitOnBarrier();
        //删除栅栏,这时多线程同步开始处理
        barrier.removeBarrier();


        Thread.sleep(1000000);
    }
}
