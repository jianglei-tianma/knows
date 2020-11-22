package com.tianma.csv.open;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class OpenCSVReader {


    public List<Object> importCsv(String inpath) throws Exception {
        List<Object> list = new ArrayList<Object>(); // 保存读取到的CSV数据

        BufferedReader reader = null;
        try {

            File file = new File(inpath); // 判断文件是否存在

            if (!file.exists()) {
                log.info("文件不存在!!!");
            } else {
                log.info("获取到文件目录，开始解析");
                reader = new BufferedReader(new FileReader(inpath)); // 读取CSV文件

                String line = null; // 循环读取每行

                while ((line = reader.readLine()) != null) {
                    // 分隔字符串（这里用到转义）,CSV大部分都是,或者|来分隔数据的，这里看情况来作决定，存储到List<Object>里
                    String[] row = line.split("\\|", -1);

                    

                }
            }

        } catch (Exception e) {

        } finally {
            reader.close();
        }

        return  list;
    }
}
