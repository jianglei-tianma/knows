package com.lagou.hdfs.client.demo.controller;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;

public class HdfsClientDemo {

    FileSystem fs = null;
    @Before
    public void init() throws Exception{
        //1 获取Hadoop 集群的configuration对象
        Configuration configuration = new Configuration();
        //configuration.set("dfs.replication", "2");
        //2 根据configuration获取Filesystem对象
        fs = FileSystem.get(new URI("hdfs://linux121:9000"), configuration, "root");
    }

    @After
    public void destory() throws Exception{
        fs.close();
    }

    @Test
    public void testMkdirs() throws Exception{
        //3 使用FileSystem对象创建一个测试目录
        fs.mkdirs(new Path("/api_test113"));
    }

    //上传文件
    @Test
    public void copyFromLocalToHdfs() throws Exception{
        //3 使用FileSystem对象创建一个测试目录
        //src:源文件目录：本地路劲
        //dst：目标文件目录，hdfs路劲
        //fs.copyFromLocalFile(new Path("D:/temp/bigdata/lagou.txt"), new Path("/lagou.txt"));
        fs.copyFromLocalFile(new Path("D:\\temp\\bigdata\\lagou.txt"), new Path("/lagou.txt"));
        //上传文件到hdfs默认是3个副本
        //如何改变上传文件的副本数量
        // 1 configuration对象中指定新的副本数量
        // 2 配置hdfs-site.xml
    }

    //下载文件
    @Test
    public void copyFromHdfsToLocal() throws Exception{
        //delSrc 是否删除源文件
        fs.copyToLocalFile(true, new Path("/lagou.txt"), new Path("D:\\temp\\bigdata\\lagou_copy.txt"));
    }


    //删除⽂件/⽂件夹
    @Test
    public void deleteFile() throws Exception{
            fs.delete(new Path("/api_test12"), true);
    }

    //查看⽂件名称、权限、⻓度、块信息

    @Test
    public void testListFiles() throws Exception{
        // 2 获取⽂件详情
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);

        while(listFiles.hasNext()){
            LocatedFileStatus status = listFiles.next();

            // 输出详情
            // ⽂件名称
            System.out.println(status.getPath().getName());
            // ⻓度
            System.out.println(status.getLen());
            // 权限
            System.out.println(status.getPermission());
            // 分组
            System.out.println(status.getGroup());

            // 获取存储的块信息
            BlockLocation[] blockLocations = status.getBlockLocations();

            for (BlockLocation blockLocation : blockLocations) {

                // 获取块存储的主机节点
                String[] hosts = blockLocation.getHosts();

                for (String host : hosts) {
                    System.out.println(host);
                }
            }

            System.out.println("-----------华丽的分割线----------");
        }

    }

    //⽂件夹判断
    @Test
    public void testListStatus() throws Exception{
        FileStatus[] listStatus = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : listStatus) {

            // 如果是⽂件
            if (fileStatus.isFile()) {
                System.out.println("f:"+fileStatus.getPath().getName());
            }else {
                System.out.println("d:"+fileStatus.getPath().getName());
            }
        }
    }

    //I/O流操作HDFS
    //⽂件上传
    @Test
    public void putFileToHDFS() throws Exception{

        // 1 获取⽂件系统
        Configuration configuration = new Configuration();
        FileSystem fs1 = FileSystem.get(new URI("hdfs://linux121:9000"), configuration, "root");
        // 2 创建输⼊流
        FileInputStream fis = new FileInputStream(new File("D:\\temp\\bigdata\\lagou.txt"));
        // 3 获取输出流
        FSDataOutputStream fos = fs1.create(new Path("/lagou_io.txt"));
        // 4 流对拷
        IOUtils.copyBytes(fis, fos, configuration);
        // 5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
    }
    //下载⽂件
    @Test
    public void getFileFromHDFS() throws Exception{

        // 1 获取⽂件系统
        Configuration configuration = new Configuration();
        FileSystem fs1 = FileSystem.get(new URI("hdfs://linux121:9000"), configuration, "root");

        // 2 获取输⼊流
        FSDataInputStream fis = fs1.open(new Path("/lagou_io.txt"));

        // 3 获取输出流
        FileOutputStream fos = new FileOutputStream(new File("D:\\temp\\bigdata\\lagou_io_copy.txt"));

        // 4 流的对拷
        IOUtils.copyBytes(fis, fos, configuration);

        // 5 关闭资源
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
        fs1.close();
    }
    //seek定位读取hdfs指定⽂件 :使⽤io流读取/lagou.txt⽂件并把内容输出两次，本质就是读取⽂件内容两次并输出
    @Test
    public void readFileSeek2() throws Exception{

        // 1 获取⽂件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://linux121:9000"),
                configuration, "root");
        // 2 打开输⼊流,读取数据输出到控制台
        FSDataInputStream in = null;
        try{
            in= fs.open(new Path("/lagou_io.txt"));
            IOUtils.copyBytes(in, System.out, 4096, false);
            in.seek(0); //从头再次读取
            IOUtils.copyBytes(in, System.out, 4096, false);
        }finally {
            IOUtils.closeStream(in);
        }
    }
    @Test
    public void testUploadPacket() throws Exception {
        // 1 获取⽂件系统
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://linux121:9000"), configuration, "root");
        //1 准备读取本地⽂件的输⼊流
        FileInputStream in = new FileInputStream(new File("D:\\temp\\bigdata\\lagou_io_copy.txt"));

        //2 准备好写出数据到hdfs的输出流
        FSDataOutputStream out = fs.create(new Path("/lagou12.txt"), new Progressable() {
            public void progress() { //这个progress⽅法就是每传输64KB（packet）就会执⾏⼀次，建立通道会执行一次
                System.out.println("&--------------------");
            }
        });
        //3 实现流拷⻉
        IOUtils.copyBytes(in, out, configuration); //默认关闭流选项是true，所以会⾃动关闭
        //4 关流 可以再次关闭也可以不关了
    }

}

































