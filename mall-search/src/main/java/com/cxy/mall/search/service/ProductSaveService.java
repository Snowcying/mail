package com.cxy.mall.search.service;

import com.cxy.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

public interface ProductSaveService {
    Boolean productStatusUp(List<SkuEsModel> skuEsModelList) throws IOException;
}
