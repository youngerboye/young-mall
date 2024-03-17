package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 产品分类
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:48:59
 */
@Data
@TableName("pms_product_category")
public class PmsProductCategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 上机分类的编号：0表示一级分类
	 */
	private Long parentId;
	/**
	 * 
	 */
	private String name;
	/**
	 * 分类级别：0->1级；1->2级
	 */
	private Integer level;
	/**
	 * 
	 */
	private Integer productCount;
	/**
	 * 
	 */
	private String productUnit;
	/**
	 * 是否显示在导航栏：0->不显示；1->显示
	 */
	private Integer navStatus;
	/**
	 * 显示状态：0->不显示；1->显示
	 */
	private Integer showStatus;
	/**
	 * 
	 */
	private Integer sort;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 
	 */
	private String keywords;
	/**
	 * 描述
	 */
	private String description;

}
