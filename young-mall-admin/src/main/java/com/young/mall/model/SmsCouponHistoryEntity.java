package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 优惠券使用、领取历史表
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:49:00
 */
@Data
@TableName("sms_coupon_history")
public class SmsCouponHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long couponId;
	/**
	 * 
	 */
	private Long memberId;
	/**
	 * 
	 */
	private String couponCode;
	/**
	 * 领取人昵称
	 */
	private String memberNickname;
	/**
	 * 获取类型：0->后台赠送；1->主动获取
	 */
	private Integer getType;
	/**
	 * 
	 */
	private LocalDateTime createTime;
	/**
	 * 使用状态：0->未使用；1->已使用；2->已过期
	 */
	private Integer useStatus;
	/**
	 * 使用时间
	 */
	private LocalDateTime useTime;
	/**
	 * 订单编号
	 */
	private Long orderId;
	/**
	 * 订单号码
	 */
	private String orderSn;

}
