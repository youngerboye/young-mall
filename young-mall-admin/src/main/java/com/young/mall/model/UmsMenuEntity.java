package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 后台菜单表
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:48:59
 */
@Data
@TableName("ums_menu")
public class UmsMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 父级ID
	 */
	private Long parentId;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 菜单名称
	 */
	private String title;
	/**
	 * 菜单级数
	 */
	private Integer level;
	/**
	 * 菜单排序
	 */
	private Integer sort;
	/**
	 * 前端名称
	 */
	private String name;
	/**
	 * 前端图标
	 */
	private String icon;
	/**
	 * 前端隐藏
	 */
	private Integer hidden;

}
