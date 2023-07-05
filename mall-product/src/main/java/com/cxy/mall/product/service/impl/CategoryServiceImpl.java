package com.cxy.mall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cxy.common.utils.PageUtils;
import com.cxy.common.utils.Query;

import com.cxy.mall.product.dao.CategoryDao;
import com.cxy.mall.product.entity.CategoryEntity;
import com.cxy.mall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Override
    public void removeMenuByIds(List<Long> asList) {
        baseMapper.deleteBatchIds(asList);

    }

    @Override
    public List<CategoryEntity> listWithTree() {
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        List<CategoryEntity> level1 = categoryEntities.stream()
                .filter(categoryEntity -> {
                    return categoryEntity.getParentCid() == 0;
                })
                .map(menu->{
                    menu.setChildren(getChildren(menu,categoryEntities));
                    return menu;
                })
                .sorted((menu1,menu2)->{
                    return (menu1.getSort()==null?0: menu1.getSort())-(menu2.getSort()==null?0:menu2.getSort());
                })
                .collect(Collectors.toList());

//        return categoryEntities;
        return level1;
    }

    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all){
        List<CategoryEntity> collect = all.stream()
                .filter(node -> {
                    return node.getParentCid().equals(root.getCatId());
                })
                .map(node -> {
                    node.setChildren(getChildren(node, all));
                    return node;
                })
                .sorted((menu1,menu2)->{
                    return (menu1.getSort()==null?0: menu1.getSort())-(menu2.getSort()==null?0:menu2.getSort());
                })
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

}