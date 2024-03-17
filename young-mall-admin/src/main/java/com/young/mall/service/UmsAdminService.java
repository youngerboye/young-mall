package com.young.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.young.mall.model.UmsAdminEntity;
import com.young.mall.vo.UserRegisterVo;

public interface UmsAdminService extends IService<UmsAdminEntity> {

    /**
     * 用户注册
     * @param userRegisterVo
     * @return
     */
    UmsAdminEntity register(UserRegisterVo userRegisterVo);
}
