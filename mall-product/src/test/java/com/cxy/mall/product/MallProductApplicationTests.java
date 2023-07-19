package com.cxy.mall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxy.mall.product.dao.AttrAttrgroupRelationDao;
import com.cxy.mall.product.entity.AttrAttrgroupRelationEntity;
import com.cxy.mall.product.entity.BrandEntity;
import com.cxy.mall.product.service.BrandService;
import com.cxy.mall.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class MallProductApplicationTests {


    @Autowired
    BrandService brandService;

    @Autowired
    AttrAttrgroupRelationDao relationDao;

//    @Autowired
//    private OSS ossClient;

    @Autowired
    CategoryService categoryService;

    @Test
    public void testUpload() throws FileNotFoundException {

    }

    @Test
    public void testFindPaths() {
        Long[] catelogPath = categoryService.findCatelogPath(225L);
        log.info("完整路径:{}", Arrays.asList(catelogPath));

    }

    @Test
    public void testSaveAttrAttrGroup() {
        AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
        attrAttrgroupRelationEntity.setAttrId(10L);
        attrAttrgroupRelationEntity.setAttrGroupId(9L);
        relationDao.insert(attrAttrgroupRelationEntity);

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
