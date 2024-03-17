package com.young.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.young.mall.model.UmsRoleResourceRelationEntity;

import java.util.List;

public interface UmsRoleResourceRelationService extends IService<UmsRoleResourceRelationEntity> {

    /**
     * 权限绑定资源
     * @param roleId
     * @param resourceIds
     * @return
     */
    Integer allocResource(Long roleId, List<Long> resourceIds);
}
