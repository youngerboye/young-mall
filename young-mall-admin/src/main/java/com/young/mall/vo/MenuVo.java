package com.young.mall.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MenuVo {

    @NotBlank(message = "菜单名称不为空")
    private String name;

    @NotNull(message = "上级菜单不能为空")
    private Long parentId;

    @NotBlank(message = "前端图标不能为空")
    private String icon;

    @NotBlank(message = "前端名称不能为空")
    private String title;

    @NotNull(message = "是否显示不能为空")
    private Integer hidden;

    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
