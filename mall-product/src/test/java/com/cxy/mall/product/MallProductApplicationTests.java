package com.cxy.mall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cxy.mall.product.dao.AttrAttrgroupRelationDao;
import com.cxy.mall.product.dao.AttrGroupDao;
import com.cxy.mall.product.dao.SkuSaleAttrValueDao;
import com.cxy.mall.product.entity.AttrAttrgroupRelationEntity;
import com.cxy.mall.product.entity.AttrGroupEntity;
import com.cxy.mall.product.entity.BrandEntity;
import com.cxy.mall.product.entity.SkuInfoEntity;
import com.cxy.mall.product.service.*;
import com.cxy.mall.product.vo.GroupWithAttr;
import com.cxy.mall.product.vo.SkuItemVo;
import com.cxy.mall.product.vo.SpuItemAttrGroupVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ExecutionException;

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

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    SkuSaleAttrValueDao skuSaleAttrValueDao;


    @Test
    public void testThreadPool() throws ExecutionException, InterruptedException {
//        long[] time=new long[100];
        for (int i = 0; i < 1; i++) {
            long start = System.currentTimeMillis();
            SkuItemVo item = skuInfoService.item(46L);
            long end = System.currentTimeMillis();
//            time[i]=end-start;
            System.out.println("花费时间" + (end - start));
        }


    }

    @Test
    public void testMyBs() {
//        System.out.println("hello");
//        List<SpuItemAttrGroupVo> attrGroupWithAttrsBySpuId = attrGroupDao.getAttrGroupWithAttrsBySpuId(13L, 225L);
//        System.out.println(attrGroupWithAttrsBySpuId);
        List<SkuItemVo.SkuItemSaleAttrVo> saleAttrsBySpuId = skuSaleAttrValueDao.getSaleAttrsBySpuId(13L);
        System.out.println(saleAttrsBySpuId);
    }

    @Test
    public void testRedisson() {
        System.out.println(redissonClient);

    }

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
