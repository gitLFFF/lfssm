package com.lf.jvm.demo.zook;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.lang.Long.MAX_VALUE;

public class TestZook {

    public static void main(String[] args) {

    }

    private ZooKeeper zooKeeper;

    @Before
    public void init() throws Exception {
        //多节点，分割无空格
        zooKeeper = new ZooKeeper("119.3.175.216:2181", 2000, new Watcher() {
            //监听回调
            @Override
            public void process(WatchedEvent watchedEvent) {

                try {
                    System.out.println("------start----");
                    List<String> children = zooKeeper.getChildren("/", true);

                    for (String cd:children) {
                        System.out.println(cd);
                    }
                    System.out.println("-------end------");
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Test
    public void createNode() {
        try {
            String s = zooKeeper.create("/lf", "myzooktest".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println(s);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getNode() {
        try {
            List<String> children = zooKeeper.getChildren("/", true);
            for (String chil : children) {
                System.out.println(chil);
            }

            Stat exists = zooKeeper.exists("/sg", false);
            System.out.println("状态信息");
            System.out.println(exists==null?"not exists":"exists");
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){

        }
    }
}
