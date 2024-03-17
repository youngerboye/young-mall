package com.young.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.young.mall.dao.UmsRoleResourceRelationDao;
import com.young.mall.model.UmsRoleResourceRelationEntity;
import com.young.mall.service.UmsRoleResourceRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsRoleResourceRelationImpl extends ServiceImpl<UmsRoleResourceRelationDao, UmsRoleResourceRelationEntity> implements UmsRoleResourceRelationService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer allocResource(Long roleId, List<Long> resourceIds) {
        //先删除
        this.remove(new LambdaQueryWrapper<UmsRoleResourceRelationEntity>().eq(UmsRoleResourceRelationEntity::getRoleId, roleId));

        //再重新保存
        List<UmsRoleResourceRelationEntity> umsRoleResourceRelationEntities = resourceIds.stream()
                .map(resourceId -> {
                    UmsRoleResourceRelationEntity umsRoleResourceRelationEntity = new UmsRoleResourceRelationEntity();
                    umsRoleResourceRelationEntity.setRoleId(roleId);
                    umsRoleResourceRelationEntity.setResourceId(resourceId);
                    return umsRoleResourceRelationEntity;
                }).collect(Collectors.toList());

        this.saveBatch(umsRoleResourceRelationEntities);

        return umsRoleResourceRelationEntities.size();
    }
}
