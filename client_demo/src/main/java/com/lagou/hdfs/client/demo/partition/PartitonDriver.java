package com.lagou.hdfs.client.demo.partition;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class PartitonDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        final Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration, "SpeakDriver");


        // 2 设置jar加载路径
        job.setJarByClass(PartitonDriver.class);
        // 3 设置map和reduce类
        job.setMapperClass(PartitionMapper.class);
        job.setReducerClass(PartitonReducer.class);
        // 4 设置map输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(PartitionBean.class);
        // 5 设置最终输出kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(PartitionBean.class);

        //设置使用自定义分区器
        job.setPartitionerClass(CustomPartitoner.class);
        //指定reducetask的数量与分区数量保持一致，分区数量是3
        job.setNumReduceTasks(2);
        // 6 设置输入和输出路径
        FileInputFormat.setInputPaths(job, new Path("D:/BigData/tianma/partition/speak.data"));//指定读取数据的原始路径
        FileOutputFormat.setOutputPath(job, new Path("D:/BigData/tianma/partition/out"));//指定结果数据输出路径
        // 7 提交
        boolean result = job.waitForCompletion(true);
        //退出JVM 正常退出是0 ，!0错误退出
        System.exit(result ? 0 : 1);
    }
}
