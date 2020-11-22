package com.lei.springboot;

import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@SpringBootTest
class SpringbootHelloworldQuickApplicationTests {

    @Test
    void contextLoads() {


        /*

         */

        Map<Object, Object> map = new HashMap<>();
        ArrayList<Object> list = new ArrayList<>();
        list.add(128);
        list.add(2);
        map.put("subscribe","1");
        map.put("openid","o6_bmjrPTlm6_2sgVt7hMZOPfL2M");
        map.put("nickname","Band");
        map.put("sex","1");
        map.put("language","zh_CN");
        map.put("city","广州");
        map.put("province","广东");

        map.put("country","中国");

        map.put("headimgurl","http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0");
        map.put("subscribe_time",1382694957);

        map.put("unionid","o6_bmasdasdsad6_2sgVt7hMZOPfL");

        map.put("remark","");
        map.put("groupid",0);
        map.put("tagid_list", list);
        map.put("subscribe_scene","ADD_SCENE_QR_CODE");
        map.put("qr_scene",98765);
        map.put("qr_scene_str","");

        System.out.println(map);

        long maxMemory = ObjectSizeCalculator.getObjectSize(map) * 10000;

        System.out.println("Map内存字节: " + maxMemory +"(字节)"+ (maxMemory/(double)1024/1024) +"MB");

    }

    @Test
    public void tset111() {


        List list = new ArrayList();
        list.add("OPENID1");
        list.add("OPENID2");
        list.add("OPENID3");
        System.out.println(list);
        JSONArray jsonArray2 = JSONArray.fromObject(list);
        System.out.println(jsonArray2);
        List<String> mapListJson = (List)jsonArray2;
        System.out.println(mapListJson);

    }

    @Test
    public void tset112() {


        List list = new ArrayList();
        list.add("OPENID1");
        list.add("OPENID2");
        list.add("OPENID3");
        System.out.println(list);
        JSONArray jsonArray2 = JSONArray.fromObject(list);
        System.out.println(jsonArray2);
        List<String> mapListJson = (List)jsonArray2;
        System.out.println(mapListJson);

    }

}
