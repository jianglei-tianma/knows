package com.lagou.hdfs.client.demo.controller;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HdfsClient {

    public static void testMkdirs() throws URISyntaxException, IOException, InterruptedException {
        //1 获取Hadoop 集群的configuration对象
        Configuration configuration = new Configuration();
        //2 根据configuration获取Filesystem对象
        FileSystem fs = FileSystem.get(new URI("hdfs://linux121:9000"), configuration, "root");

        //3 使用FileSystem对象创建一个测试目录
        fs.mkdirs(new Path("/api_test"));
        //4 释放FileSystem对象（类似数据库连接）
        fs.close();
    }

    public static void testMkdirs12() throws URISyntaxException, IOException, InterruptedException {
        //1 获取Hadoop 集群的configuration对象
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://linux121:9000");
        //2 根据configuration获取Filesystem对象
        FileSystem fs = FileSystem.get(configuration);
        //3 使用FileSystem对象创建一个测试目录
        fs.mkdirs(new Path("/api_test12"));
        //4 释放FileSystem对象（类似数据库连接）
        fs.close();
    }

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        // testMkdirs();
        testMkdirs12();
    }

}
