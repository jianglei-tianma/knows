package com.lagou.hdfs.hadoop.demo.partition;

import com.lagou.hdfs.hadoop.demo.partition.PartitionBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

//reduce输入类型：Text, PartitionBean输出类型：Text, PartitionBean
public class PartitonReducer extends Reducer<Text, PartitionBean, Text, PartitionBean> {

    @Override
    protected void reduce(Text key, Iterable<PartitionBean> values, Context context) throws IOException, InterruptedException {

        //无需集合运算，只需要进行输出即可
        for (PartitionBean value : values) {

            context.write(key, value);
        }
    }
}
