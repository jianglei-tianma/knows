package com.lagou.hdfs.client.demo.comment.step2;
//自定义inputformat读取多个小文件合并为一个SequenceFile文件

//SequenceFile文件中以kv形式存储文件，key--》文件路径+文件名称，value-->文件的整个内容

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;
import java.util.List;


//TextInputFormat中泛型是LongWritable：文本的偏移量, Text：一行文本内容；指明当前inputformat的输出数据类型
//自定义inputformat：key-->文件路径+名称，value-->整个文件内容
public class MergeCustomInputFormat extends FileInputFormat<Text, BytesWritable> {

    //重写是否可切分
    @Override
    protected boolean isSplitable(JobContext context, Path filename) {
        //对于当前需求，不需要把文件切分，保证一个切片就是一个文件
        return false;
    }

    //分片逻辑依然是原始的分片逻辑，一个文件一个maptask
    //jvm重用优化，uber模式，小文件任务优化
    @Override
    public List<InputSplit> getSplits(JobContext job) throws IOException {
        return super.getSplits(job);
    }

    //recordReader就是用来读取数据的对象
    @Override
    public RecordReader<Text, BytesWritable> createRecordReader(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {
        MergeCustomRecordReader recordReader = new MergeCustomRecordReader();
        //调用recordReader的初始化方法
        recordReader.initialize(split, context);
        return recordReader;
    }
}
