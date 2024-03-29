package com.lagou.hdfs.hadoop.demo.map_join;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MapJoinDriver {


    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        // 1 获取配置信息，或者job对象实例
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration, "MapJoinDriver");
        // 2 指定本程序的jar包所在的本地路径
        job.setJarByClass(MapJoinDriver.class);
        // 3 指定本业务job要使用的mapper/Reducer业务类
        job.setMapperClass(MapJoinMapper.class);

        // 5 指定最终输出的数据的kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        // 6 指定job的输入原始文件所在目录
        FileInputFormat.setInputPaths(job, new Path("D:/mr/output/speak"));
        FileOutputFormat.setOutputPath(job, new Path("D:/mr/output/speak/sortout"));
        //设置加载缓存文件
        job.addCacheFile(new URI("file:///e:/map_join/cache/position.txt"));
        job.setNumReduceTasks(0);

        // 7 将job中配置的相关参数，以及job所用的java类所在的jar包， 提交给yarn去运行
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
}
