package com.lagou.hdfs.client.demo.comment.setp1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class CombineDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "CombineDriver");
        job.setJarByClass(CombineDriver.class);
        job.setMapperClass(CombineMapper.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        //指定inputformat
        job.setInputFormatClass(CombineTextInputFormat.class);
        //指定分片大小
        CombineTextInputFormat.setMaxInputSplitSize(job, 1024 * 1024 * 4);
        //指定输入，输出路径
        FileInputFormat.setInputPaths(job, new Path("E:\\teach\\hadoop框架\\资料\\data\\mr综合案例\\input"));
        FileOutputFormat.setOutputPath(job, new Path("E:\\teach\\hadoop框架\\资料\\data\\mr综合案例\\merge-out"));
        //指定reducetask的数量
        job.setNumReduceTasks(3);
        boolean b = job.waitForCompletion(true);
        if (b) {
            System.exit(0);
        }
    }
}
