package com.lagou.hdfs.client.demo.sort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

//因为这个类的实例对象要作为map输出的key，所以要实现writablecomparalbe接口
public class SpeakBean implements WritableComparable<SpeakBean> {
    private Long selfDuration; //自由内容时长
    private Long thirdPartDuration;//第三方内容时长
    private String deviceId; //设备id
    private Long sumDuration;//总时长

    public SpeakBean() {
    }

    public SpeakBean(Long selfDuration, Long thirdPartDuration, String deviceId, Long sumDuration) {
        this.selfDuration = selfDuration;
        this.thirdPartDuration = thirdPartDuration;
        this.deviceId = deviceId;
        this.sumDuration = sumDuration;
    }

    //指定排序规则
    //重写compareTo方法，此处是比较一个字段如果比较2个字段就是二次排序
    @Override
    public int compareTo(SpeakBean o) {
        //0 相等 -1 大于 1 小于
        int result;
       // 按照总流量大小，倒序排列
        if (sumDuration > o.getSumDuration()) {
            result = -1;
        } else if (sumDuration < o.getSumDuration()) {
            result = 1;
        } else {
            result = 0; //加入第二个判断条件，二次排序
        }
        return result;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(selfDuration);
        out.writeLong(thirdPartDuration);
        out.writeUTF(deviceId);
        out.writeLong(sumDuration);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.selfDuration = in.readLong();
        this.thirdPartDuration = in.readLong();
        this.deviceId = in.readUTF();
        this.sumDuration = in.readLong();
    }
    public Long getSelfDuration() {
        return selfDuration;
    }

    public void setSelfDuration(Long selfDuration) {
        this.selfDuration = selfDuration;
    }

    public Long getThirdPartDuration() {
        return thirdPartDuration;
    }

    public void setThirdPartDuration(Long thirdPartDuration) {
        this.thirdPartDuration = thirdPartDuration;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Long getSumDuration() {
        return sumDuration;
    }

    public void setSumDuration(Long sumDuration) {
        this.sumDuration = sumDuration;
    }

    @Override
    public String toString() {
        return selfDuration +
                "\t" + thirdPartDuration +
                "\t" + deviceId + '\'' +
                "\t" + sumDuration;
    }
}
