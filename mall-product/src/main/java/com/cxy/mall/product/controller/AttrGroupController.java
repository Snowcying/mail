package com.cxy.mall.product.controller;

import com.cxy.common.utils.PageUtils;
import com.cxy.common.utils.R;
import com.cxy.mall.product.entity.AttrAttrgroupRelationEntity;
import com.cxy.mall.product.entity.AttrEntity;
import com.cxy.mall.product.entity.AttrGroupEntity;
import com.cxy.mall.product.service.AttrAttrgroupRelationService;
import com.cxy.mall.product.service.AttrGroupService;
import com.cxy.mall.product.service.AttrService;
import com.cxy.mall.product.service.CategoryService;
import com.cxy.mall.product.vo.AttrGroupRelationVo;
import com.cxy.mall.product.vo.GroupWithAttr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 属性分组
 *
 * @author cxy
 * @email sunlightcs@gmail.com
 * @date 2023-06-20 21:42:00
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrService attrService;

    @Autowired
    private AttrAttrgroupRelationService relationService;

    // api/product/attrgroup/225/withattr
    @RequestMapping("/{catelogId}/withattr")
    public R getWithAttr(@PathVariable("catelogId") Long catelogId) {

        List<AttrGroupEntity> groupEntities = attrGroupService.getGroupIdsByCatelogId(catelogId);
        List<List<AttrAttrgroupRelationEntity>> attrIds = relationService.getAttrIdsByGroupIds(groupEntities);
        List<GroupWithAttr> data = attrService.getListByIds(attrIds);


        return R.ok().put("data", data);
    }

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("catelogId") Long catelogId) {
//        PageUtils page = attrGroupService.queryPage(params);
        PageUtils page = attrGroupService.queryPage(params, catelogId);

        return R.ok().put("page", page);
    }

    ///product/attrgroup/{attrgroupId}/attr/relation
    @GetMapping("/{attrgroupId}/attr/relation")
    //@RequiresPermissions("product:attrgroup:list")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId) {
        List<AttrEntity> list = attrService.getRelationAttr(attrgroupId);
        return R.ok().put("data", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);
        Long catelogId = attrGroup.getCatelogId();
        Long[] path = categoryService.findCatelogPath(catelogId);
        attrGroup.setCatelogPath(path);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

    // /product/attrgroup/attr/relation/delete
    @RequestMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] vos) {
        attrService.deleteRelation(vos);
        return R.ok();
    }

    ///product/attrgroup/{attrgroupId}/noattr/relation
    @RequestMapping("/{attrGroupId}/noattr/relation")
    //@RequiresPermissions("product:attrgroup:info")
    public R attrNoRelation(@RequestParam Map<String, Object> params, @PathVariable("attrGroupId") Long attrGroupId) {
        PageUtils page = attrService.getNoRelationAttr(params, attrGroupId);

        return R.ok().put("page", page);
    }

    // /product/attrgroup/attr/relation
    @RequestMapping("/attr/relation")
    public R addRelation(@RequestBody List<AttrGroupRelationVo> vos) {
//        attrService.addRelation(vos);
        relationService.addRelation(vos);
        return R.ok();
    }

}
