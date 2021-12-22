package com.lagou.hdfs.hadoop.demo.service;

import com.lagou.hdfs.hadoop.demo.utils.DateUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.TimerTask;

public class LogCollectorTask extends TimerTask {
    @Override
    public void run() {
        //采集业务逻辑
        //1、扫描指定目录，找到待上传文件
        File logsDir = new File("D:\\BigData\\logs");
        File[] uploadFiles = logsDir.listFiles((FilenameFilter) (dir, name) -> {
            return name.startsWith("access.log.");
        });
        //2、把待上传文件转移到临时目录
        //判断临时目录是哦福存在
        File tmpFile = new File("D:\\BigData\\log_tmp");
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
        for (File file : uploadFiles) {
            file.renameTo(new File(tmpFile.getPath()+"/" + file.getName()));
        }
        //3、使用hdfs api上传日志文件到指定目录
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://linux121:9000");



        FileSystem fs = null;
        try {
            fs = FileSystem.get(configuration);
            //判断hdfs目录路劲是否存在，备份目录是否存在
            Path path = new Path("/collect_log/" + DateUtils.getCurrentDate());
            if (!fs.exists(path)) {
                fs.mkdirs(path);
            }
            File bakFolder = new File("D:\\BigData\\log_bak\\" + DateUtils.getCurrentDate());
            if (!bakFolder.exists()) {
                bakFolder.mkdirs();
            }
            File[] files = tmpFile.listFiles();
            for (File file : files) {
                //按照日期分门别列存放
                fs.copyFromLocalFile(new Path(file.getPath()), new Path(path.toString() + "/" + file.getName()));
                //4、上传后的文件转移到备份目录
                file.renameTo(new File(bakFolder.getPath() + "/" + file.getName()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

