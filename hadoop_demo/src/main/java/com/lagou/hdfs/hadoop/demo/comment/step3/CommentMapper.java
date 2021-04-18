package com.lagou.hdfs.client.demo.comment.step3;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

//第一对kv：使用sequenceFileinputFormat读取，所以key：Text，Value：BytesWritable(生成的sequenceFile文件指定就是这种类型 )
public class CommentMapper extends Mapper<Text, BytesWritable, CommentBean, NullWritable> {

    //key:文件名
    //value：一个文件的完整内容
    @Override
    protected void map(Text key, BytesWritable value, Context context) throws IOException, InterruptedException {
        //区分每一行
        String[] lines = value.toString().split("\\n");
        for (String line : lines) {
            //每一行进行切分
            CommentBean commentBean = parseStrToCommentBean(line);
            if (commentBean != null) {
                context.write(commentBean, NullWritable.get());
            }
        }
    }

    //切分字符串封装成commentbean对象
    public CommentBean parseStrToCommentBean(String line) {
        if (StringUtils.isNotBlank(line)) {
            String[] fields = line.split("\t");
            if (fields.length > 9) {
                String orderId = fields[0];
                String comment = fields[1];
                String commentExt = fields[2];
                int goodsNum = Integer.parseInt(fields[3]);
                String phoneNum = fields[4];
                String userName = fields[5];
                String address = fields[6];
                int commentStatus = Integer.parseInt(fields[7]);
                String commentTime = fields[8];
                return new CommentBean(orderId, comment, commentExt, goodsNum, phoneNum, userName, address, commentStatus, commentTime);
            } else {
                return null;
            }
        }
        return null;
    }
}
