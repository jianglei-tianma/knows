package com.lei.yunlong.generator.my.controller;

import com.github.pagehelper.PageInfo;
import com.lei.yunlong.generator.my.bean.Admin;
import com.lei.yunlong.generator.my.service.AdminService;
import com.lei.yunlong.generator.my.utils.Resp;
import com.lei.yunlong.generator.my.vo.resp.AdminListRespsonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/get")
    public Resp<AdminListRespsonVo> getAdmin() {
        AdminListRespsonVo adminListRespsonVo = new AdminListRespsonVo();
        PageInfo<Admin> admins = adminService.selectAll();
        adminListRespsonVo.setPageInfo(admins);
        return Resp.ok(adminListRespsonVo);


    }


}
