package com.lagou.hdfs.client.demo.partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
//Partitoner分区器的泛型是map输出的kv类型
public class CustomPartitoner extends Partitioner<Text, PartitionBean> {

    @Override
    public int getPartition(Text text, PartitionBean partitionBean, int numPartitions) {
        int partiton = 0;
        if (text.toString().equals("kar")) {
            //只需要保证满足此if条件的数据获得同个分区编号集合
            partiton = 0;
        } else if (text.toString().equals("pandora")) {
            partiton = 1;
        } else {
            partiton = 2;
        }
        return partiton;
    }
}
