package com.lagou.hdfs.client.demo.partition;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PartitionMapper extends Mapper<LongWritable, Text, Text, PartitionBean> {

    final PartitionBean bean = new PartitionBean();
    final Text text = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] fields = value.toString().split("\t");
        String appkey = fields[2];

        bean.setId(fields[0]);
        bean.setDeviceId(fields[1]);
        bean.setAppkey(fields[2]);
        bean.setIp(fields[3]);
        bean.setSelfDuration(Long.parseLong(fields[4]));
        bean.setThirPartDuration(Long.parseLong(fields[5]));
        bean.setStatus(fields[6]);
        text.set(appkey);

        context.write(text, bean); //shuffle开始会根据ke的hashcode值进行分区，但是结合我们自己的业务，默认hash分区方式不满足我们的要求

    }
}
