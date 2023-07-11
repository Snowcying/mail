package com.cxy.mall.product.exception;

import com.cxy.common.exception.BizCodeEnum;
import com.cxy.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
//@ControllerAdvice(basePackages = "com.cxy.mall.product.controller")
//@ResponseBody
@RestControllerAdvice(basePackages = "com.cxy.mall.product.controller")
public class mallExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题{},异常类型：{}", e.getMessage(), e.getClass());
        BindingResult result = e.getBindingResult();
        HashMap<String, String> errorMap = new HashMap<>();
        result.getFieldErrors().forEach((item) -> {
            errorMap.put(item.getField(), item.getDefaultMessage());
        });
        return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(), BizCodeEnum.VALID_EXCEPTION.getMsg()).put("data", errorMap);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable){
        return R.error(BizCodeEnum.UNKNOWN_EXCEPTION.getCode(), BizCodeEnum.UNKNOWN_EXCEPTION.getMsg());
    }
}
