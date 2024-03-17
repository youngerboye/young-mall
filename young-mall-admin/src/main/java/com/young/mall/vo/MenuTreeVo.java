package com.young.mall.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class MenuTreeVo {

    private Long id;
    private Long parentId;
    private String createTime;
    private String title;
    private Integer level;
    private Integer sort;
    private String name;
    private String icon;
    private Integer hidden;

    private List<MenuTreeVo> children;
}
