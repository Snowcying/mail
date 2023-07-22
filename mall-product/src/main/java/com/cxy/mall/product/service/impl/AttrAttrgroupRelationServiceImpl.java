package com.cxy.mall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxy.common.utils.PageUtils;
import com.cxy.common.utils.Query;
import com.cxy.mall.product.dao.AttrAttrgroupRelationDao;
import com.cxy.mall.product.entity.AttrAttrgroupRelationEntity;
import com.cxy.mall.product.entity.AttrGroupEntity;
import com.cxy.mall.product.service.AttrAttrgroupRelationService;
import com.cxy.mall.product.vo.AttrGroupRelationVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("attrAttrgroupRelationService")
public class AttrAttrgroupRelationServiceImpl extends ServiceImpl<AttrAttrgroupRelationDao, AttrAttrgroupRelationEntity> implements AttrAttrgroupRelationService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrAttrgroupRelationEntity> page = this.page(
                new Query<AttrAttrgroupRelationEntity>().getPage(params),
                new QueryWrapper<AttrAttrgroupRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void addRelation(List<AttrGroupRelationVo> vos) {
        List<AttrAttrgroupRelationEntity> collect = vos.stream().map(item -> {
            AttrAttrgroupRelationEntity attrgroupRelation = new AttrAttrgroupRelationEntity();
//            attrgroupRelation.setAttrId(item.getAttrId());
//            attrgroupRelation.setAttrGroupId(item.getAttrGroupId());
            BeanUtils.copyProperties(item, attrgroupRelation);
            return attrgroupRelation;
        }).collect(Collectors.toList());
        this.saveBatch(collect);
    }

    @Override
    public List<List<AttrAttrgroupRelationEntity>> getAttrIdsByGroupIds(List<AttrGroupEntity> groupEntities) {
        List<List<AttrAttrgroupRelationEntity>> ans = new ArrayList<>();
        for (AttrGroupEntity item : groupEntities) {
            List<AttrAttrgroupRelationEntity> entities = this.baseMapper.selectList(new QueryWrapper<AttrAttrgroupRelationEntity>().eq("attr_group_id", item.getAttrGroupId()));
            if (entities.size() > 0) {
                ans.add(entities);
            }
        }
        return ans;
    }

}