package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 限时购场次表
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:48:59
 */
@Data
@TableName("sms_flash_promotion_session")
public class SmsFlashPromotionSessionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@TableId
	private Long id;
	/**
	 * 场次名称
	 */
	private String name;
	/**
	 * 每日开始时间
	 */
	private LocalDateTime startTime;
	/**
	 * 每日结束时间
	 */
	private LocalDateTime endTime;
	/**
	 * 启用状态：0->不启用；1->启用
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

}
