package com.lagou.hdfs.client.demo.speak;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SpeakReducer extends Reducer<Text, SpeakBean, Text, SpeakBean> {

    @Override
    protected void reduce(Text key, Iterable<SpeakBean> values, Context context) throws IOException, InterruptedException {

        Long self_duration = 0L;
        Long third_part_duration = 0L;
        for (SpeakBean bean : values) {
            Long selfDuration = bean.getSelfDuration();
            Long thirdPartDuration = bean.getThirdPartDuration();
            self_duration += selfDuration;
            third_part_duration += thirdPartDuration;
        }
        SpeakBean bean = new SpeakBean(self_duration, third_part_duration, key.toString());
        context.write(key, bean);
    }
}
