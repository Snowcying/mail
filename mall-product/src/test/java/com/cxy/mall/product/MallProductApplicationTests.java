package com.cxy.mall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxy.mall.product.entity.BrandEntity;
import com.cxy.mall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class MallProductApplicationTests {


    @Autowired
    BrandService brandService;

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
