package com.lagou.hdfs.hadoop.demo.group;

import lombok.Getter;
import lombok.Setter;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
@Setter
@Getter
public class OrderBean implements WritableComparable<OrderBean> {

    private String orderId;//订单id
    private Double price;//金额


    public OrderBean(String orderId, Double price) {
        this.orderId = orderId;
        this.price = price;
    }

    public OrderBean() {
    }

    //指定排序规则，先按照订单id比较再按照金额比较,按照金额降序排
    @Override
    public int compareTo(OrderBean o) {
        int res = this.orderId.compareTo(o.getOrderId()); //0 1 -1
        if (res == 0) {
            //订单id相同，比较金额
            res = - this.price.compareTo(o.getPrice());

        }
        return res;
    }

    //序列化
    @Override
    public void write(DataOutput out) throws IOException {

        out.writeUTF(orderId);
        out.writeDouble(price);
    }

    //反序列化
    @Override
    public void readFields(DataInput in) throws IOException {
        this.orderId = in.readUTF();
        this.price = in.readDouble();
    }

    //重写toString()

    @Override
    public String toString() {
        return orderId + '\t' +
                price
                ;
    }


}
