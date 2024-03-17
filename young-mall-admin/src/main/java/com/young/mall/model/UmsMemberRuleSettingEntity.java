package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 会员积分成长规则表
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:48:59
 */
@Data
@TableName("ums_member_rule_setting")
public class UmsMemberRuleSettingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 连续签到天数
	 */
	private Integer continueSignDay;
	/**
	 * 连续签到赠送数量
	 */
	private Integer continueSignPoint;
	/**
	 * 每消费多少元获取1个点
	 */
	private BigDecimal consumePerPoint;
	/**
	 * 最低获取点数的订单金额
	 */
	private BigDecimal lowOrderAmount;
	/**
	 * 每笔订单最高获取点数
	 */
	private Integer maxPointPerOrder;
	/**
	 * 类型：0->积分规则；1->成长值规则
	 */
	private Integer type;

}
