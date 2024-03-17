package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 话题表
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:49:00
 */
@Data
@TableName("cms_topic")
public class CmsTopicEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long categoryId;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private LocalDateTime createTime;
	/**
	 * 
	 */
	private LocalDateTime startTime;
	/**
	 * 
	 */
	private LocalDateTime endTime;
	/**
	 * 参与人数
	 */
	private Integer attendCount;
	/**
	 * 关注人数
	 */
	private Integer attentionCount;
	/**
	 * 
	 */
	private Integer readCount;
	/**
	 * 奖品名称
	 */
	private String awardName;
	/**
	 * 参与方式
	 */
	private String attendType;
	/**
	 * 话题内容
	 */
	private String content;

}
