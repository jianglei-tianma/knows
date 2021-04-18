package com.lagou.hdfs.client.demo.output;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;


public class CustomWriter extends RecordWriter<Text, NullWritable> {
    //定义成员变量
    private FSDataOutputStream lagouOut;
    private FSDataOutputStream otherOut;

    //定义构造方法接收两个输出流


    public CustomWriter(FSDataOutputStream lagouOut, FSDataOutputStream otherOut) {
        this.lagouOut = lagouOut;
        this.otherOut = otherOut;
    }

    //写出数据的逻辑,控制写出的路径
    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        //写出数据需要输出流
        final String line = key.toString();
        if (line.contains("lagou")) {
            lagouOut.write(line.getBytes());
            lagouOut.write("\r\n".getBytes());
        } else {
            otherOut.write(line.getBytes());
            otherOut.write("\r\n".getBytes());
        }
    }

    //关闭，释放资源
    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {

        IOUtils.closeStream(lagouOut);
        IOUtils.closeStream(otherOut);
    }
}
