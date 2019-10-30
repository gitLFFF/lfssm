package com.lf.jvm.demo.zook;

import org.apache.zookeeper.*;

import java.io.IOException;


public class DistributeServer {
    private ZooKeeper zooKeeper;

    public static void main(String[] args) {
        DistributeServer server = new DistributeServer();

        //1连接zookeeper集群
        server.getConnect();

        //2注册节点
        System.out.println();
        server.register(args[0]);

        //3业务逻辑
        try {
            server.business();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    private void register(String hostName) {
        try {
            String path = zooKeeper.create("/servers/server", hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(path);
            System.out.println(hostName+"已经上线");
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getConnect() {
        try {
            zooKeeper = new ZooKeeper("119.3.175.216:2181", 2000, new Watcher() {
                //监听回调
                @Override
                public void process(WatchedEvent watchedEvent) {


                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
