package com.lagou.hdfs.hadoop.demo.reduce_join;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.springframework.beans.BeanUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ReduceJoinReducer extends Reducer<Text, DeliverBean, DeliverBean, NullWritable> {


    @Override
    protected void reduce(Text key, Iterable<DeliverBean> values, Context context) throws IOException, InterruptedException {
        //相同positionId的bean对象放到一起（1个职位数据，n个投递行为数据）

        List<DeliverBean> deBeans = new ArrayList<>();
        DeliverBean positionBean = new DeliverBean();
        for (DeliverBean bean : values) {
            String flag = bean.getFlag();
            if (flag.equals("deliver")) {
                //投递行为数据
                //此处不能直接添加，需要深度拷贝
                DeliverBean newBean = new DeliverBean();
                BeanUtils.copyProperties(bean, newBean);
                deBeans.add(newBean);
            } else {
                //职位数据
                BeanUtils.copyProperties(bean, positionBean);

            }
        }
        //遍历投递行为数据
        for (DeliverBean bean : deBeans) {
            bean.setPositionName(positionBean.getPositionName());
            context.write(bean, NullWritable.get());
        }

    }
}
