package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 积分消费设置
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:48:59
 */
@Data
@TableName("ums_integration_consume_setting")
public class UmsIntegrationConsumeSettingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 每一元需要抵扣的积分数量
	 */
	private Integer deductionPerAmount;
	/**
	 * 每笔订单最高抵用百分比
	 */
	private Integer maxPercentPerOrder;
	/**
	 * 每次使用积分最小单位100
	 */
	private Integer useUnit;
	/**
	 * 是否可以和优惠券同用；0->不可以；1->可以
	 */
	private Integer couponStatus;

}
