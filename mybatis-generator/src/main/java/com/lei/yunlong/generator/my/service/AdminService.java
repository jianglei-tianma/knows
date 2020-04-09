package com.lei.yunlong.generator.my.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lei.yunlong.generator.my.bean.Admin;
import com.lei.yunlong.generator.my.dao.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminMapper adminMapper;

    public PageInfo<Admin> selectAll() {

        List<Admin> admins = adminMapper.selectAll();

        PageHelper.startPage(1, 10);
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(admins);
        return pageInfo;
    }


    public void create(Integer count) {

        BigDecimal bigDecimal = new BigDecimal(count).multiply(new BigDecimal(5));


    }

}
