package com.lagou.hdfs.client.demo.comment.step3;

import lombok.Getter;
import lombok.Setter;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Setter
@Getter
public class CommentBean implements WritableComparable<CommentBean> {

    private String orderId;
    private String comment;
    private String commentExt;
    private int goodsNum;
    private String phoneNum;
    private String userName;
    private String address;
    private int commentStatus;
    private String commentTime;

    public CommentBean() {

    }

    public CommentBean(String orderId, String comment, String commentExt, int goodsNum, String phoneNum, String userName, String address, int commentStatus, String commentTime) {
        this.orderId = orderId;
        this.comment = comment;
        this.commentExt = commentExt;
        this.goodsNum = goodsNum;
        this.phoneNum = phoneNum;
        this.userName = userName;
        this.address = address;
        this.commentStatus = commentStatus;
        this.commentTime = commentTime;
    }

    //定义排序规则，按照时间降序
    @Override
    public int compareTo(CommentBean o) {
        return o.getCommentTime().compareTo(this.commentTime);
    }
    //序列化
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(orderId);
        out.writeUTF(comment);
        out.writeUTF(commentExt);
        out.writeInt(goodsNum);
        out.writeUTF(phoneNum);
        out.writeUTF(userName);
        out.writeUTF(address);
        out.writeInt(commentStatus);
        out.writeUTF(commentTime);

    }
    //反序列化
    @Override
    public void readFields(DataInput in) throws IOException {
        this.orderId = in.readUTF();
        this.comment = in.readUTF();
        this.commentExt = in.readUTF();
        this.goodsNum = in.readInt();
        this.phoneNum = in.readUTF();
        this.userName = in.readUTF();
        this.address = in.readUTF();
        this.commentStatus = in.readInt();
        this.commentTime = in.readUTF();
    }

    @Override
    public String toString() {
        return orderId + "\t" + comment + "\t" + commentExt + "\t" + goodsNum + "\t" + phoneNum + "\t" + userName + "\t" + address + "\t" + commentStatus + "\t" + commentTime;
    }
}
