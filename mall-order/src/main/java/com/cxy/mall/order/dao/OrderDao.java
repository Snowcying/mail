package com.cxy.mall.order.dao;

import com.cxy.mall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author cxy
 * @email sunlightcs@gmail.com
 * @date 2023-06-25 16:05:32
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
