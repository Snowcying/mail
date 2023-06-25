package com.cxy.mall.member.dao;

import com.cxy.mall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author cxy
 * @email sunlightcs@gmail.com
 * @date 2023-06-25 15:58:36
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
