package com.lagou.hdfs.client.demo.reduce_join;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class ReduceJoinMapper extends Mapper<LongWritable, Text, Text, DeliverBean> {

    Text text = new Text();
    String name = "";
    DeliverBean bean = new DeliverBean();
    //map任务启动时，初始化执行一次
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        InputSplit inputSplit = context.getInputSplit();
        FileSplit split = (FileSplit) inputSplit;
        name = split.getPath().getName();

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] arr = line.split("\t");

        if (name.startsWith("deliver_info")) {
            //读取的是投递行为数据
            bean.setUserId(arr[0]);
            bean.setPositionId(arr[1]);
            bean.setDate(arr[2]);

            //先把空属性置为空字符串
            bean.setPositionName("");
            bean.setFlag("deliver");

        } else {

            bean.setUserId("");
            bean.setPositionId(arr[0]);
            bean.setDate("");
            bean.setPositionName(arr[1]);
            bean.setFlag("position");

        }
        text.set(bean.getPositionId());
        context.write(text, bean);
    }
}
