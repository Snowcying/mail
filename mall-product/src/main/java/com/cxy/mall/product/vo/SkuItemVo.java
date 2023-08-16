package com.cxy.mall.product.vo;

import com.cxy.mall.product.entity.SkuImagesEntity;
import com.cxy.mall.product.entity.SkuInfoEntity;
import com.cxy.mall.product.entity.SpuInfoDescEntity;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class SkuItemVo {
    // pms_sku_info
    SkuInfoEntity info;

    boolean hasStock = true;

    // pms_sku_images
    List<SkuImagesEntity> images;

    // spu销售属性组合
    List<SkuItemSaleAttrVo> saleAttr;

    // spu 介绍
    SpuInfoDescEntity desp;

    // spu规格参数
    List<SpuItemAttrGroupVo> groupAttrs;

    @Data
    @ToString
    public static class SkuItemSaleAttrVo {
        private Long attrId;
        private String attrName;
        private String attrValues;
    }
}
