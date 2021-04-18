package com.lagou.hdfs.hadoop.demo.task.job1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * jianglei
 */
public class SortJobMapper extends Mapper<LongWritable, Text, LongWritable, NullWritable> {

    LongWritable outKey = new LongWritable();

    /**
     * 按行处理数据
     * 一行是数字，在mapper的过程中把Text类型的输入数据转换为LongWritable作为mapper的输出key,
     * 借助shuffle过程中的默认自动排序来实现需求中的数据整体排序
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        outKey.set(Long.parseLong(value.toString()));
        context.write(outKey, NullWritable.get());
    }
}




