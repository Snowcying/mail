package com.cxy.mall.search.controller;

import com.cxy.common.exception.BizCodeEnum;
import com.cxy.common.to.es.SkuEsModel;
import com.cxy.common.utils.R;
import com.cxy.mall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("search/save")
//@Slf4j
public class ElasticSaveController {
    @Autowired
    private ProductSaveService productSaveService;

    @GetMapping("/1")
    public R test() {
        return R.ok();
    }


    // 上架商品
    @PostMapping("/product")
    public R productStatusUp(@RequestBody List<SkuEsModel> skuEsModelList) {
        Boolean b = false;
        try {
            b = productSaveService.productStatusUp(skuEsModelList);
        } catch (Exception e) {
//            log.error("ElasticController商品上架错误:{}",e);
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnum.PRODUCT_UP_EXCEPTION.getMsg());
        }
        if (!b) {
            return R.ok();
        } else {
            return R.error(BizCodeEnum.PRODUCT_UP_EXCEPTION.getCode(), BizCodeEnum.PRODUCT_UP_EXCEPTION.getMsg());
        }
    }
}
