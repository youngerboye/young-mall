package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 产品阶梯价格表(只针对同商品)
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:49:00
 */
@Data
@TableName("pms_product_ladder")
public class PmsProductLadderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long productId;
	/**
	 * 满足的商品数量
	 */
	private Integer count;
	/**
	 * 折扣
	 */
	private BigDecimal discount;
	/**
	 * 折后价格
	 */
	private BigDecimal price;

}
