package com.lagou.hdfs.hadoop.demo.task.job1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 驱动类
 */
public class SortJobDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
 /*
        1. 获取配置文件对象，获取job对象实例
        2. 指定程序jar的本地路径
        3. 指定Mapper/Reducer类
        4. 指定Mapper输出的kv数据类型
        5. 指定最终输出的kv数据类型
        6. 指定job处理的原始数据路径
        7. 指定job输出结果路径
        8. 提交作业
         */
//        1. 获取配置文件对象，获取job对象实例
        final Configuration conf = new Configuration();

        final Job job = Job.getInstance(conf, "SortJobDriver");
//        2. 指定程序jar的本地路径
        job.setJarByClass(SortJobMapper.class);
//        3. 指定Mapper/Reducer类
        job.setMapperClass(SortJobMapper.class);
        job.setReducerClass(SortJobReducer.class);
//        4. 指定Mapper输出的kv数据类型
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(NullWritable.class);
//        5. 指定最终输出的kv数据类型
        job.setOutputKeyClass(LongWritable.class);
        job.setOutputValueClass(LongWritable.class);
        //需求指定要求输出以及结果文件
        job.setNumReduceTasks(1);
//        6. 指定job处理的原始数据路径
        //import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
        //import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
        FileInputFormat.setInputPaths(job, new Path("hadoop_demo/src/main/resources/input/task/job1")); //指定读取数据的原始路径
//        7. 指定job输出结果路径
        FileOutputFormat.setOutputPath(job, new Path("hadoop_demo/src/main/resources/output/task/job1")); //指定结果数据输出路径
//        8. 提交作业
        final boolean flag = job.waitForCompletion(true);
        //jvm退出：正常退出0，非0值则是错误退出
        System.exit(flag ? 0 : 1);


    }
}
