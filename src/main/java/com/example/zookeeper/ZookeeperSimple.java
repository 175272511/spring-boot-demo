package com.example.zookeeper;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liuhui on 2016/11/11.
 */
public class ZookeeperSimple implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

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

        //删除节点
//        zooKeeper.delete();
        countDownLatch.await();

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
     * 创建节点回调
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