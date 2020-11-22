package com.tianma.csv;

import com.tianma.csv.open.OpenCSVReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CsvApplicationTests {

    @Autowired
    private OpenCSVReader openCSVReader;
    @Test
    void contextLoads() {
        String inpath="D:\\task_rule@201908.csv";
        try {
            openCSVReader.importCsv(inpath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
