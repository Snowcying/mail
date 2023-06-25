package com.cxy.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxy.common.utils.PageUtils;
import com.cxy.mall.coupon.entity.HomeAdvEntity;

import java.util.Map;

/**
 * 首页轮播广告
 *
 * @author cxy
 * @email sunlightcs@gmail.com
 * @date 2023-06-25 15:21:11
 */
public interface HomeAdvService extends IService<HomeAdvEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

