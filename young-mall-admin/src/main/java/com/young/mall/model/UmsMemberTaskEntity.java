package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 会员任务表
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:48:59
 */
@Data
@TableName("ums_member_task")
public class UmsMemberTaskEntity implements Serializable {
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
	 * 赠送成长值
	 */
	private Integer growth;
	/**
	 * 赠送积分
	 */
	private Integer intergration;
	/**
	 * 任务类型：0->新手任务；1->日常任务
	 */
	private Integer type;

}
