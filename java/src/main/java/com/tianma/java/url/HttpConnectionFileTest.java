package com.tianma.java.url;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class HttpConnectionFileTest {


    public static void main(String[] args) {
        String filePath = getFilePath("D:\\temp");
        String url = "http://wk.baidu.com.cn/view/9e747b0aba1aa8114431d9ad?page=home";
        String savePath = filePath + "baidu.txt";
        try {
            Boolean aBoolean = saveUrlAs(url, savePath);
            log.info("执行成功：[{}]", aBoolean);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getFilePath(String filePath) {

        File file = new File(filePath);
        //判断文件夹是否存在
        if (!file.exists())
        {
            //如果文件夹不存在，则创建新的的文件夹
            file.mkdirs();
        }
        //判断文件的保存路径后面是否以/结尾
        if (!filePath.endsWith("/")) {
            filePath += "/";
        }
        return filePath;
    }

    public static Boolean saveUrlAs(String url, String savePath) throws Exception {

        HttpURLConnection conn = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        Boolean successFlag = false;
        try
        {
            // 建立链接
            URL httpUrl=new URL(url);
            conn = (HttpURLConnection) httpUrl.openConnection();
            //以Post方式提交表单，默认get方式
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setReadTimeout(5 * 1000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //连接指定的资源
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //获取网络输入流
                bis = new BufferedInputStream(conn.getInputStream());
                int contentLength = conn.getContentLength();
                log.info("获取文件的大小：[{}]" , contentLength / (1024) + "KB");
                bos = new BufferedOutputStream(new FileOutputStream(savePath));
                //循环读写
                byte[] buf = new byte[2 * 1024];
                int len = 0;
                while ((len = bis.read(buf)) != -1) {
                    bos.write(buf, 0, len);
                }
                bos.flush();
                successFlag = true;
                log.info("文件下载成功");
            }
        } catch (Exception e) {
            log.error("下载文件异常:[{}]" , e);
        } finally {
            //资源释放
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                log.error("关闭流异常:[{}]" , e);
            }
        }
        return successFlag;
    }
}
