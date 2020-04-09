package com.lei.yunlong.generator.my.vo.resp;

import com.github.pagehelper.PageInfo;
import com.lei.yunlong.generator.my.bean.Admin;
import lombok.Data;

@Data
public class AdminListRespsonVo {

    private PageInfo<Admin> pageInfo;
}
