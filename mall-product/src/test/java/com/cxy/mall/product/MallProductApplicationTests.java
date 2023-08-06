package com.cxy.mall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxy.mall.product.dao.AttrAttrgroupRelationDao;
import com.cxy.mall.product.entity.AttrAttrgroupRelationEntity;
import com.cxy.mall.product.entity.AttrGroupEntity;
import com.cxy.mall.product.entity.BrandEntity;
import com.cxy.mall.product.entity.SkuInfoEntity;
import com.cxy.mall.product.service.*;
import com.cxy.mall.product.vo.GroupWithAttr;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.util.*;

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

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    AttrGroupService attrGroupService;

    @Autowired
    AttrAttrgroupRelationService attrAttrgroupRelationService;

    @Autowired
    AttrService attrService;

    @Autowired
    SkuInfoService skuInfoService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedis() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set("hello", "world" + UUID.randomUUID().toString());

        String hello = ops.get("hello");
        System.out.println(hello);
    }


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
        list.forEach((item) -> {
            System.out.println(item);
        });
    }

    @Test
    void testBrandsList() {
        Map<String, Object> stringLongMap = new HashMap<>();
//        stringLongMap.put("catId",225);
        List<BrandEntity> brand = categoryBrandRelationService.getBrand(225L);
        log.info(brand.toString());
    }

    @Test
    void getWithAttr() {
        Long catelogId = 225L;
        List<AttrGroupEntity> groupEntities = attrGroupService.getGroupIdsByCatelogId(catelogId);
        List<List<AttrAttrgroupRelationEntity>> attrIds = attrAttrgroupRelationService.getAttrIdsByGroupIds(groupEntities);
        List<GroupWithAttr> data = attrService.getListByIds(attrIds);
    }

    @Test
    void getSkuBySpu() {
        Long id = 11L;
        List<SkuInfoEntity> skusBySpuId = skuInfoService.getSkusBySpuId(id);
        log.info(skusBySpuId.toString());
    }

    @Test
    void selectSearchAttrs() {
        List<Long> ids = Arrays.asList(7L,
                8L,
                13L,
                14L,
                15L,
                16L);
        List<Long> longs = attrService.selectSearchAttrs(ids);
        log.info(longs.toString());
    }
}
