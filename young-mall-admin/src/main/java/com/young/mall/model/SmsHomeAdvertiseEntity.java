package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 首页轮播广告表
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:48:59
 */
@Data
@TableName("sms_home_advertise")
public class SmsHomeAdvertiseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 轮播位置：0->PC首页轮播；1->app首页轮播
	 */
	private Integer type;
	/**
	 * 
	 */
	private String pic;
	/**
	 * 
	 */
	private LocalDateTime startTime;
	/**
	 * 
	 */
	private LocalDateTime endTime;
	/**
	 * 上下线状态：0->下线；1->上线
	 */
	private Integer status;
	/**
	 * 点击数
	 */
	private Integer clickCount;
	/**
	 * 下单数
	 */
	private Integer orderCount;
	/**
	 * 链接地址
	 */
	private String url;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 排序
	 */
	private Integer sort;

}
