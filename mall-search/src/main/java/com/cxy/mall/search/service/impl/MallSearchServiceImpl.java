package com.cxy.mall.search.service.impl;

import com.cxy.mall.search.config.MallESConfig;
import com.cxy.mall.search.constant.EsConstant;
import com.cxy.mall.search.service.MallSearchService;
import com.cxy.mall.search.vo.SearchParam;
import com.cxy.mall.search.vo.SearchResult;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MallSearchServiceImpl implements MallSearchService {
    @Autowired
    RestHighLevelClient client;

    @Override
    public SearchResult search(SearchParam param) {
        SearchResult result = null;
        SearchRequest searchRequest = buildSearchRequest(param);

        try {
            SearchResponse response = client.search(searchRequest, MallESConfig.COMMON_OPTIONS);

            result = buildSearchResult(response);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    private SearchResult buildSearchResult(SearchResponse response) {
        return null;

    }

    private SearchRequest buildSearchRequest(SearchParam param) {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(param.getKeyword())) {
            boolQuery.must(QueryBuilders.matchQuery("skuTitle", param.getKeyword()));
        }
        if (param.getCatalog3Id() != null) {
            boolQuery.filter(QueryBuilders.termQuery("catalogId", param.getCatalog3Id()));
        }
        if (param.getBrandId() != null && param.getBrandId().size() > 0) {
            boolQuery.filter(QueryBuilders.termsQuery("brandId", param.getBrandId()));
        }

        boolQuery.filter(QueryBuilders.termQuery("hasStock", param.getHasStock() == 1));

        // attrs
        if (param.getAttrs() != null && param.getAttrs().size() > 0) {
            for (String attrStr : param.getAttrs()) {
                BoolQueryBuilder nestBoolQuery = QueryBuilders.boolQuery();
                String[] s = attrStr.split("_");
                String attrId = s[0]; // id
                String[] attrValues = s[1].split(":"); // val
                nestBoolQuery.must(QueryBuilders.termQuery("attrs.attrId", attrId));
                nestBoolQuery.must(QueryBuilders.termsQuery("attrs.attrValue", attrValues));
                NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("attrs", nestBoolQuery, ScoreMode.None);
                boolQuery.filter(nestedQuery);
            }
        }

        // price
        if (!StringUtils.isEmpty(param.getSkuPrice())) {
            //1_500/_500/500_
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("skuPrice");
            String[] s = param.getSkuPrice().split("_");
            if (s.length == 2) {
                rangeQuery.gte(s[0]).lte(s[1]);
            } else if (s.length == 1) {
                if (param.getSkuPrice().startsWith("_")) {
                    rangeQuery.lte(s[0]);
                }
                if (param.getSkuPrice().endsWith("_")) {
                    rangeQuery.gte(s[0]);
                }
            }
            boolQuery.filter(rangeQuery);
        }

        sourceBuilder.query(boolQuery);


        SearchRequest searchRequest = new SearchRequest(new String[]{EsConstant.PRODUCT_INDEX}, sourceBuilder);
        return searchRequest;
    }
}
