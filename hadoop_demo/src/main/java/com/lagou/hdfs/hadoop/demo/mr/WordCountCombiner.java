package com.lagou.hdfs.hadoop.demo.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

//combiner组件的输入和暑促类型与Map()方法保持一致
public class WordCountCombiner extends Reducer<Text, IntWritable, Text, IntWritable> {
    IntWritable total = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //进行局部汇总，逻辑是与reduce方法保持一致
        int num = 0;
        for (IntWritable value : values) {
            int i = value.get();
            num += i;
        }

        total.set(num);
        context.write(key, total);
    }
}
