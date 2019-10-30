package com.lf.jvm.demo.zook;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DistributeClient {
    private ZooKeeper zooKeeper;

    public static void main(String[] args) {
        DistributeClient client = new DistributeClient();

        //1连接zookeeper集群
        client.getConnect();

        //2监听注册节点
        client.watchRegister();

        //3业务逻辑
        try {
            client.business();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void business() throws InterruptedException {

        Thread.sleep(Long.MAX_VALUE);
    }

    private void watchRegister() {
        try {
            List<String> children = zooKeeper.getChildren("/servers", true);
            ArrayList<String> hosts = new ArrayList<>();
            for (String node : children) {
                byte[] data = zooKeeper.getData("/servers/" + node, false, null);
                hosts.add(new String(data));
                //System.out.println(data);
//                for (String host: hosts) {
//                    System.out.println("在线主机："+host);
//                }
                System.out.println(hosts);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getConnect() {
        try {
            //监听回调
            zooKeeper = new ZooKeeper("119.3.175.216:2181", 2000, watchedEvent -> {
                watchRegister();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

