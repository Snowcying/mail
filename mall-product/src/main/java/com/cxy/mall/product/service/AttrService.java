package com.cxy.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cxy.common.utils.PageUtils;
import com.cxy.mall.product.entity.AttrAttrgroupRelationEntity;
import com.cxy.mall.product.entity.AttrEntity;
import com.cxy.mall.product.vo.AttrGroupRelationVo;
import com.cxy.mall.product.vo.AttrRespVo;
import com.cxy.mall.product.vo.AttrVo;
import com.cxy.mall.product.vo.GroupWithAttr;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author cxy
 * @email sunlightcs@gmail.com
 * @date 2023-06-20 21:09:17
 */
public interface AttrService extends IService<AttrEntity> {


    PageUtils queryPage(Map<String, Object> params, Long catelogId, String type);

    void saveAttr(AttrVo attr);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    PageUtils queryPage(Map<String, Object> params, Long catelogId);

    List<AttrEntity> getRelationAttr(Long attrgroupId);

    void deleteRelation(AttrGroupRelationVo[] vos);

    PageUtils getNoRelationAttr(Map<String, Object> params, Long attrGroupId);

    List<GroupWithAttr> getListByIds(List<List<AttrAttrgroupRelationEntity>> attrIds);

    List<Long> selectSearchAttrs(List<Long> attrIds);
}

