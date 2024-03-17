package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 公司收发货地址表
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:49:00
 */
@Data
@TableName("oms_company_address")
public class OmsCompanyAddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 地址名称
	 */
	private String addressName;
	/**
	 * 默认发货地址：0->否；1->是
	 */
	private Integer sendStatus;
	/**
	 * 是否默认收货地址：0->否；1->是
	 */
	private Integer receiveStatus;
	/**
	 * 收发货人姓名
	 */
	private String name;
	/**
	 * 收货人电话
	 */
	private String phone;
	/**
	 * 省/直辖市
	 */
	private String province;
	/**
	 * 市
	 */
	private String city;
	/**
	 * 区
	 */
	private String region;
	/**
	 * 详细地址
	 */
	private String detailAddress;

}
