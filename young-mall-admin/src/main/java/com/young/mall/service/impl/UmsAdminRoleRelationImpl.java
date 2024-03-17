package com.young.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.young.mall.dao.UmsAdminRoleRelationDao;
import com.young.mall.model.UmsAdminRoleRelationEntity;
import com.young.mall.service.UmsAdminRoleRelationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsAdminRoleRelationImpl extends ServiceImpl<UmsAdminRoleRelationDao, UmsAdminRoleRelationEntity> implements UmsAdminRoleRelationService {
    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        this.remove(new LambdaQueryWrapper<UmsAdminRoleRelationEntity>()
                .eq(UmsAdminRoleRelationEntity::getAdminId, adminId));

        List<UmsAdminRoleRelationEntity> umsAdminRoleRelationEntityList = roleIds.stream()
                .map(roleId -> {
                    UmsAdminRoleRelationEntity umsAdminRoleRelationEntity = new UmsAdminRoleRelationEntity();
                    umsAdminRoleRelationEntity.setAdminId(adminId);
                    umsAdminRoleRelationEntity.setRoleId(roleId);

                    return umsAdminRoleRelationEntity;
                }).collect(Collectors.toList());

        this.saveBatch(umsAdminRoleRelationEntityList);
        return umsAdminRoleRelationEntityList.size();
    }
}
