package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 专题评论表
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:49:00
 */
@Data
@TableName("cms_subject_comment")
public class CmsSubjectCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long subjectId;
	/**
	 * 
	 */
	private String memberNickName;
	/**
	 * 
	 */
	private String memberIcon;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private LocalDateTime createTime;
	/**
	 * 
	 */
	private Integer showStatus;

}
