package com.cxy.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxy.common.utils.PageUtils;
import com.cxy.mall.member.entity.MemberLoginLogEntity;

import java.util.Map;

/**
 * 会员登录记录
 *
 * @author cxy
 * @email sunlightcs@gmail.com
 * @date 2023-06-25 15:58:36
 */
public interface MemberLoginLogService extends IService<MemberLoginLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

