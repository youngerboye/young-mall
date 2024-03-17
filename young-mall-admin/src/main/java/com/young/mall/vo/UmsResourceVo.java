package com.young.mall.vo;

import com.young.mall.valid.group.AddValid;
import com.young.mall.valid.group.UpdateValid;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class UmsResourceVo {

    @Null(message = "新建时为空",groups = AddValid.class)
    @NotBlank(message = "编辑时不能为空",groups = UpdateValid.class)
    private Long id;

    @NotBlank(message = "资源名称不能为空")
    private String name;

    @NotBlank(message = "资源路径不能为空")
    private String url;

    @NotNull(message = "资源分类不能为空")
    private Long categoryId;


    private String description;
}
