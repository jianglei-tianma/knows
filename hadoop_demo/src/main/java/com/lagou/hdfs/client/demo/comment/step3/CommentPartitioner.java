package com.lagou.hdfs.client.demo.comment.step3;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class CommentPartitioner extends Partitioner<CommentBean, NullWritable> {
    @Override
    public int getPartition(CommentBean commentBean, NullWritable nullWritable, int numPartitions) {
        //0,1,2 -->对应分区编号的
        return (commentBean.getCommentStatus() & Integer.MAX_VALUE) % numPartitions;
    }
}
