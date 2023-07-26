package com.cxy.mall.ware.vo;

import lombok.Data;

@Data
public class PurchaseDoneItemVo {
    // {"itemId":1,"status":3,"reason":""},
    private Long itemId;
    private Integer status;
    private String reason;
}
