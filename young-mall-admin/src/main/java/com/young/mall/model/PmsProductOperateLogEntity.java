package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:49:00
 */
@Data
@TableName("pms_product_operate_log")
public class PmsProductOperateLogEntity implements Serializable {
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
	 * 
	 */
	private BigDecimal priceOld;
	/**
	 * 
	 */
	private BigDecimal priceNew;
	/**
	 * 
	 */
	private BigDecimal salePriceOld;
	/**
	 * 
	 */
	private BigDecimal salePriceNew;
	/**
	 * 赠送的积分
	 */
	private Integer giftPointOld;
	/**
	 * 
	 */
	private Integer giftPointNew;
	/**
	 * 
	 */
	private Integer usePointLimitOld;
	/**
	 * 
	 */
	private Integer usePointLimitNew;
	/**
	 * 操作人
	 */
	private String operateMan;
	/**
	 * 
	 */
	private LocalDateTime createTime;

}
