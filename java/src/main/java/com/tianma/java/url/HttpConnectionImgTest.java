
package com.tianma.java.url;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 从远程服务器上下载图片到本地
 */
@Slf4j
public class HttpConnectionImgTest {

    public static void main(String[] args) {
        //Url代表一个统一资源定位符，它只想
        HttpURLConnection httpURLConnection = null;
        FileOutputStream fileOutputStream = null;
        try {

            //1、将网址封装成url对象
            URL url = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605367292983&di=0392280cab696343708990551ec049bb&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F8694a4c27d1ed21bda98e624af6eddc450da3f9a.jpg");
            //2、构建链接对象HttpURLConnection
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(5 * 1000);
            //判断服务器端正常的反馈是否已经到达客户端
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                //3、获得网络字节输入流对象
                InputStream inputStream = httpURLConnection.getInputStream();
                //4、构建FileOutputStream对象将图片下载到本地
                fileOutputStream = new FileOutputStream(new File("D:\\temp\\dowload.jpg"));
                //5、循环读写
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, len);
                }
                fileOutputStream.flush();
                log.info("恭喜资源下载完成");
            }
        } catch (Exception e) {
            //6、资源释放

                try {
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }

                    if (httpURLConnection != null) {
                        //中断连接
                        httpURLConnection.disconnect();
                    }
                } catch (IOException ex) {
                    log.error("关闭连接异常:[{}]" ,e);
                }


        }
    }
}
