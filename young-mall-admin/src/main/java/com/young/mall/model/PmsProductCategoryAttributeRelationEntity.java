package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类）
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:48:59
 */
@Data
@TableName("pms_product_category_attribute_relation")
public class PmsProductCategoryAttributeRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long productCategoryId;
	/**
	 * 
	 */
	private Long productAttributeId;

}
