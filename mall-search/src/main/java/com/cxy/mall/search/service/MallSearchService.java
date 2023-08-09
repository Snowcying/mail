package com.cxy.mall.search.service;

import com.cxy.mall.search.vo.SearchParam;
import com.cxy.mall.search.vo.SearchResult;

public interface MallSearchService {
    SearchResult search(SearchParam param);
}
