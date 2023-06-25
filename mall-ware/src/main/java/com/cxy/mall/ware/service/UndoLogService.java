package com.cxy.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxy.common.utils.PageUtils;
import com.cxy.mall.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author cxy
 * @email sunlightcs@gmail.com
 * @date 2023-06-25 16:08:23
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

