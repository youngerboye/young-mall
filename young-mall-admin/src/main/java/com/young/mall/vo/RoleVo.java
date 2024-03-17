package com.young.mall.vo;

import com.young.mall.valid.group.AddValid;
import com.young.mall.valid.group.UpdateValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class RoleVo {

    @NotNull(groups = UpdateValid.class, message = "id不能为空")
    @Null(groups = AddValid.class, message = "id必须为空")
    private Long id;

    @NotBlank(message = "角色名称不能为空")
    private String name;

    private String description;

    @NotNull(message = "是否启用不能为空")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
