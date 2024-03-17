package com.young.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.young.mall.model.UmsRoleMenuRelationEntity;

import java.util.List;

public interface UmsRoleMenuRelationService extends IService<UmsRoleMenuRelationEntity> {

    /**
     * 权限绑定菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    Integer allocMenu(Long roleId, List<Long> menuIds);
}
