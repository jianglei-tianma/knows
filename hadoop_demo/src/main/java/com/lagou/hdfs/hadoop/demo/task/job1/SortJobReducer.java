package com.lagou.hdfs.hadoop.demo.task.job1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SortJobReducer extends Reducer<LongWritable, NullWritable, LongWritable, LongWritable> {

    long index = 0;
    LongWritable resultKey = new LongWritable();
    @Override
    protected void reduce(LongWritable key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {

        //遍历values，肯能存在相等的数字
        for (NullWritable value: values) {
            index++;
            resultKey.set(index);
            context.write(resultKey, key);
        }
    }
}
