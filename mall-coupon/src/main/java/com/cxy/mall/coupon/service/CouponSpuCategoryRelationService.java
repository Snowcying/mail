package com.cxy.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxy.common.utils.PageUtils;
import com.cxy.mall.coupon.entity.CouponSpuCategoryRelationEntity;

import java.util.Map;

/**
 * 优惠券分类关联
 *
 * @author cxy
 * @email sunlightcs@gmail.com
 * @date 2023-06-25 15:21:11
 */
public interface CouponSpuCategoryRelationService extends IService<CouponSpuCategoryRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

