package com.lagou.hdfs.hadoop.demo.speak;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
//四个参数：分为两对kv
//第一对kv：map输入参数的kv类型；k-->一行文本偏移量，v-->一行文本内容
//第二队kv：map输出参数kv类型：k-->map输出的key类型，v:map输出的value类型

public class SpeakMapper extends  Mapper<LongWritable, Text, Text, SpeakBean> {

    /**
     * 1转换接受到的text数据为String
     * 2按照制表符进行切分；得到自由内容时长，第三方内容时长，设备id，封装为SpeakBean
     * 3直接输出：k-->设备id，value-->speakBean
     */

    final Text device_id = new Text();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //1转换接受到的text数据为String
        String line = value.toString() ;
        //2按照制表符进行切分；得到自由内容时长，第三方内容时长，设备id，封装为SpeakBean
        String[] fileds = line.split("\t");

        String selfDuration = fileds[fileds.length - 3];
        String thirdPartDuration = fileds[fileds.length - 2];
        String deviceId = fileds[1];
        //3直接输出：k-->设备id，value-->speakBean
        SpeakBean bean = new SpeakBean(Long.parseLong(selfDuration), Long.parseLong(thirdPartDuration), deviceId);
        device_id.set(deviceId);
        context.write(device_id, bean);

    }
}
