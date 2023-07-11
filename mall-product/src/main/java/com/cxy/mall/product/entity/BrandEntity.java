package com.cxy.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.cxy.common.valid.AddGroup;
import com.cxy.common.valid.ListValue;
import com.cxy.common.valid.UpdateGroup;
import com.cxy.common.valid.UpdateStatus;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 * 
 * @author cxy
 * @email sunlightcs@gmail.com
 * @date 2023-06-20 21:09:17
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@TableId
	@Null(message = "新增不能指定id",groups = {AddGroup.class})
	@NotNull(message = "修改必须指定id",groups = {UpdateGroup.class})
	private Long brandId;
	/**
	 * 品牌名
	 */
	@NotBlank(message = "品牌名必须提交",groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@URL(message = "logo必须是一个合法的URL地址",groups = {AddGroup.class, UpdateGroup.class})
	@NotEmpty(groups = {AddGroup.class})
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@ListValue(vals = {0,1},groups = {AddGroup.class, UpdateStatus.class})
	@NotNull(groups = {AddGroup.class, UpdateStatus.class})
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
	@Pattern(regexp = "^[a-zA-Z]$",message = "检索首字母必须是一个字母",groups = {AddGroup.class, UpdateGroup.class})
	@NotEmpty(groups = {AddGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
	@Min(value = 0,message = "排序必须大于等于0",groups = {AddGroup.class, UpdateGroup.class})
	@NotNull(groups = {AddGroup.class})
	private Integer sort;

}
