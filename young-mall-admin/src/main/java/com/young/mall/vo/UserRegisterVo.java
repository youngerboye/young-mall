package com.young.mall.vo;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("注册用户vo")
public class UserRegisterVo {

    @NotBlank(message = "用户名不能为空")
    private String username;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 邮箱
     */
    @Email
    private String email;
    /**
     *
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    @NotNull(message = "状态不能为空")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
