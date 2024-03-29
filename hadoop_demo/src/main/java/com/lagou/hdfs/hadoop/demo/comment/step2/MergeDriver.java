package com.lagou.hdfs.client.demo.comment.step2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.DefaultCodec;
import org.apache.hadoop.io.compress.SnappyCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

public class MergeDriver {
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

        final Job job = Job.getInstance(conf, "MergeDriver");
//        2. 指定程序jar的本地路径
        job.setJarByClass(MergeDriver.class);
//        3. 指定Mapper/Reducer类
        job.setMapperClass(MergeMapper.class);
        job.setReducerClass(MergeReducer.class);
//        4. 指定Mapper输出的kv数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);
//        5. 指定最终输出的kv数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);

        //设置使用自定义InputFormat读取数据
        job.setInputFormatClass(MergeCustomInputFormat.class);
        FileInputFormat.setInputPaths(job, new Path("D:\\BigData\\正式班(1)\\第一阶段\\模块一\\资料\\mr综合案例\\input")); //指定读取数据的原始路径
        //指定输出使用的outputformat
        job.setOutputFormatClass(SequenceFileOutputFormat.class);
        //尽可能降低数据的量，减少磁盘空间的占用，网络间通信时数据量小可以节省时间
        //针对sequencefile的压缩
        //SequenceFileOutputFormat.setOutputCompressorClass(job, CompressionCodec.class);
        //SequenceFileOutputFormat.setOutputCompressorClass(job, SnappyCodec.class);

        SequenceFileOutputFormat.setOutputCompressorClass(job, DefaultCodec.class);
        //压缩类型
        SequenceFileOutputFormat.setOutputCompressionType(job, SequenceFile.CompressionType.RECORD);
        //SequenceFileOutputFormat.setOutputCompressionType(job, SequenceFile.CompressionType.BLOCK);

//        7. 指定job输出结果路径
        FileOutputFormat.setOutputPath(job, new Path("D:\\BigDataOut\\Merge\\out")); //指定结果数据输出路径
//        8. 提交作业
        final boolean flag = job.waitForCompletion(true);
        //jvm退出：正常退出0，非0值则是错误退出
        System.exit(flag ? 0 : 1);
    }
}
