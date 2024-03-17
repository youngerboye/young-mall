package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 成长值变化历史记录表
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:48:59
 */
@Data
@TableName("ums_growth_change_history")
public class UmsGrowthChangeHistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long memberId;
	/**
	 * 
	 */
	private LocalDateTime createTime;
	/**
	 * 改变类型：0->增加；1->减少
	 */
	private Integer changeType;
	/**
	 * 积分改变数量
	 */
	private Integer changeCount;
	/**
	 * 操作人员
	 */
	private String operateMan;
	/**
	 * 操作备注
	 */
	private String operateNote;
	/**
	 * 积分来源：0->购物；1->管理员修改
	 */
	private Integer sourceType;

}
