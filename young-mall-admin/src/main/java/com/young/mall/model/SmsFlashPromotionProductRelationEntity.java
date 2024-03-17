package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品限时购与商品关系表
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:48:59
 */
@Data
@TableName("sms_flash_promotion_product_relation")
public class SmsFlashPromotionProductRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long flashPromotionId;
	/**
	 * 编号
	 */
	private Long flashPromotionSessionId;
	/**
	 * 
	 */
	private Long productId;
	/**
	 * 限时购价格
	 */
	private BigDecimal flashPromotionPrice;
	/**
	 * 限时购数量
	 */
	private Integer flashPromotionCount;
	/**
	 * 每人限购数量
	 */
	private Integer flashPromotionLimit;
	/**
	 * 排序
	 */
	private Integer sort;

}
