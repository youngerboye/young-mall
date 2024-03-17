package com.young.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.young.mall.dao.UmsRoleMenuRelationDao;
import com.young.mall.model.UmsRoleMenuRelationEntity;
import com.young.mall.service.UmsRoleMenuRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsRoleMenuRelationImpl extends ServiceImpl<UmsRoleMenuRelationDao, UmsRoleMenuRelationEntity> implements UmsRoleMenuRelationService {


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer allocMenu(Long roleId, List<Long> menuIds) {
        //先删除
        this.remove(new LambdaQueryWrapper<UmsRoleMenuRelationEntity>().eq(UmsRoleMenuRelationEntity::getRoleId, roleId));
        //再重新保存
        List<UmsRoleMenuRelationEntity> roleMenuRelationEntityList = menuIds.stream()
                .map(menuId -> {
                    UmsRoleMenuRelationEntity umsRoleMenuRelationEntity = new UmsRoleMenuRelationEntity();
                    umsRoleMenuRelationEntity.setRoleId(roleId);
                    umsRoleMenuRelationEntity.setMenuId(menuId);
                    return umsRoleMenuRelationEntity;
                }).collect(Collectors.toList());

        this.saveBatch(roleMenuRelationEntityList);

        return roleMenuRelationEntityList.size();
    }


}
