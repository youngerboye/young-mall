package com.young.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.young.mall.model.UmsAdminRoleRelationEntity;

import java.util.List;

public interface UmsAdminRoleRelationService extends IService<UmsAdminRoleRelationEntity> {

    /*
    更新用户角色
     */
    int updateRole(Long adminId, List<Long> roleIds);
}
