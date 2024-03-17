package com.young.mall.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户举报表
 * 
 * @author young
 * @email shineyoung1991@gmail.com
 * @date 2024-03-17 11:49:00
 */
@Data
@TableName("cms_member_report")
public class CmsMemberReportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 举报类型：0->商品评价；1->话题内容；2->用户评论
	 */
	private Integer reportType;
	/**
	 * 举报人
	 */
	private String reportMemberName;
	/**
	 * 
	 */
	private LocalDateTime createTime;
	/**
	 * 
	 */
	private String reportObject;
	/**
	 * 举报状态：0->未处理；1->已处理
	 */
	private Integer reportStatus;
	/**
	 * 处理结果：0->无效；1->有效；2->恶意
	 */
	private Integer handleStatus;
	/**
	 * 
	 */
	private String note;

}
