package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户和标签关系表
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:48:59
 */
@Data
@TableName("ums_member_member_tag_relation")
public class UmsMemberMemberTagRelationEntity implements Serializable {
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
	private Long tagId;

}
