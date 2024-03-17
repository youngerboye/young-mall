package com.young.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.young.mall.model.UmsRoleEntity;
import com.young.mall.vo.RoleVo;

public interface UmsRoleService extends IService<UmsRoleEntity> {

    /**
     * 创建角色
     * @param roleVo 角色对象
     */
    void creat(RoleVo roleVo);

}
