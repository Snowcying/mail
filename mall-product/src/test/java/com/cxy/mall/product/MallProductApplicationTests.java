package com.cxy.mall.product;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxy.mall.product.entity.BrandEntity;
import com.cxy.mall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class MallProductApplicationTests {


    @Autowired
    BrandService brandService;

    @Autowired
    private OSS ossClient;

    @Test
    public void testUpload() throws FileNotFoundException {
//        String endpoint = "oss-cn-chengdu.aliyuncs.com";
//        String accessKeyId="LTAI5t7PT1Sf7STnkYSf7bZC";
//        String accessKeySecret="3yaKsySEPS1wfTO3jY9Hn5l6wXwQxd";
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        FileInputStream inputStream = new FileInputStream("C:\\Users\\chenxinyi\\Pictures\\Saved Pictures\\2.JPG");
        ossClient.putObject("gulimall-snowcying","bug3.JPG",inputStream);
        ossClient.shutdown();
        System.out.println("上传完成...");
    }

    @Test
    void contextLoads() {
//        BrandEntity brandEntity = new BrandEntity();
//        brandEntity.setBrandId(9L);
//        brandEntity.setDescript("huawei666");
//
//        brandService.updateById(brandEntity);
//        brandEntity.setName("huawei");
//        brandService.save(brandEntity);
//        System.out.println("保存成功");
        List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 9L));
        list.forEach((item)->{
            System.out.println(item);
        });
    }

}
