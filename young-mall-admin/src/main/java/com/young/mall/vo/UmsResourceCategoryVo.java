package com.young.mall.vo;

import javax.validation.constraints.NotBlank;

public class UmsResourceCategoryVo {

    @NotBlank(message = "资源分类模块名称不能为空")
    private String name;

    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
