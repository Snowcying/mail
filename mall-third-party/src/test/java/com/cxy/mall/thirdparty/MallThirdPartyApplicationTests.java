package com.cxy.mall.thirdparty;

import com.aliyun.oss.OSS;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class MallThirdPartyApplicationTests {
    @Autowired
    private OSS ossClient;

    @Test
    void contextLoads() {
    }

    @Test
    public void testUpload() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\chenxinyi\\Pictures\\Saved Pictures\\2.JPG");
        Date date = new Date();//获取当前的日期
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String str = df.format(date);//获取String类型的时间

        String filename= "pic"+str+".JPG";
        ossClient.putObject("gulimall-snowcying",filename,inputStream);
        ossClient.shutdown();
        System.out.println("上传完成...");
    }

}
