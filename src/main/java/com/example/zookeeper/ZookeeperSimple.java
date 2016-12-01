package com.example.zookeeper;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liuhui on 2016/11/11.
 */
public class ZookeeperSimple implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static Stat stat = new Stat();

    /**
     * 接收服务端事件通知
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()){
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws Exception {
        //创建链接
        ZooKeeper zooKeeper = new ZooKeeper("192.168.1.205:2181",5000, new ZookeeperSimple());
        //权限管理,防止其他应用调用, 注意: 在删除操作中,只会对该节点的子节点具有权限管理, 该节点还是可以在无授权的情况下删除,其他操作不会.
        zooKeeper.addAuthInfo("digest", "权限测试:true".getBytes());
        System.out.println(zooKeeper.getState());

        /**
         * 同步创建节点
         * path:节点
         * data:节点可以带上数据
         * acl:策略,如果对权限没有要求,一般传Ids.OPEN_ACL_UNSAFE即可
         * mode:节点类型:
         *            持久 PERSISTENT(0, false, false),
         *            持久顺序  PERSISTENT_SEQUENTIAL(2, false, true),
         *            临时  EPHEMERAL(1, true, false),
         *            临时顺序  EPHEMERAL_SEQUENTIAL(3, true, true);
         */
        String randomStr = RandomStringUtils.randomAlphabetic(5);
        String path = zooKeeper.create("/zk-test-" + randomStr, "测试".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        //节点不支持递归创建,需要保证父节点已存在,否则抛异常KeeperException$NoNodeException
        String path1 = zooKeeper.create("/zk-test-" + randomStr + "-", "测试".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("创建节点:" + path);
        System.out.println("创建节点:" + path1);

        countDownLatch.await();

        //获取子节点
        String path2 = "/zk-test-child";
        zooKeeper.create(path2, "测试".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create(path2 + "/c1", "测试".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        List<String> children = zooKeeper.getChildren(path2, true);
        System.out.println("子节点: " + children.toString());

        //获取节点数据
        zooKeeper.getData(path2, true, stat);
        //更新节点数据, -1指基于最新版本更新操作, 如果更新没有原子性要求可用-1
        Stat _stat = zooKeeper.setData(path2, "test".getBytes(), -1);
        //根据上一版本进行更新,如果版本不对会报错, 可保证原子性操作
        zooKeeper.setData(path2, "test".getBytes(), _stat.getVersion());
        //删除节点
        zooKeeper.delete(path2, -1);
        //检测节点存在,更新和删除, 不会检测子节点
        zooKeeper.exists(path2, true);

        /**
         * 创建异步节点
         * callback: 回调
         * context: 内容
         */
        String randomStr1 = RandomStringUtils.randomAlphabetic(5);
        zooKeeper.create(
                "/zk-test-" + randomStr1,
                "测试".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                new IStringCallback(),
                "context test"
                );
        Thread.sleep(10000);

    }
}


class IStringCallback implements AsyncCallback.StringCallback{

    /**
     * 异步创建节点回调
     * @param rc 响应码 0成功 -4断开连接 -110节点已存在 -112会话已过期
     * @param path  节点路径
     * @param ctx   内容
     * @param name  实际节点名, 如果节点需要按顺序,则无法知道实际名字
     */
    @Override
    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println(rc);
        System.out.println(path);
        System.out.println(ctx);
        System.out.println(name);

    }
}