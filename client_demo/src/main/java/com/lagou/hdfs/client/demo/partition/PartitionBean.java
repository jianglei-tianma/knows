package com.lagou.hdfs.client.demo.partition;

import lombok.Getter;
import lombok.Setter;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Setter
@Getter
public class PartitionBean implements Writable {
    //日志id
    private String id;
    //设备id
    private String deviceId;
    //appkey厂商id
    private String appkey;
    //ip地址
    private String ip;
    //自由内容播放时长
    private Long selfDuration;
    //第三方内容时长
    private Long thirPartDuration;
    //状态码
    private String status;

    public PartitionBean() {
    }

    public PartitionBean(String id, String deviceId, String appkey, String ip, Long selfDuration, Long thirPartDuration, String status) {
        this.id = id;
        this.deviceId = deviceId;
        this.appkey = appkey;
        this.ip = ip;
        this.selfDuration = selfDuration;
        this.thirPartDuration = thirPartDuration;
        this.status = status;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(id);
        out.writeUTF(deviceId);
        out.writeUTF(appkey);
        out.writeUTF(ip);
        out.writeLong(selfDuration);
        out.writeLong(thirPartDuration);
        out.writeUTF(status);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.id = in.readUTF();
        this.deviceId = in.readUTF();
        this.appkey = in.readUTF();
        this.ip = in.readUTF();
        this.selfDuration = in.readLong();
        this.thirPartDuration = in.readLong();
        this.status = in.readUTF();
    }

    @Override
    public String toString() {
        return  id + "\t" + deviceId + "\t" + appkey + "\t" + ip + "\t" + selfDuration + "\t" + thirPartDuration + "\t" + status;
    }
}
