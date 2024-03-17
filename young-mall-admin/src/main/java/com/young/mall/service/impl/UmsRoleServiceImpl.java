package com.young.mall.service.impl;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.young.mall.common.exception.ApiException;
import com.young.mall.dao.UmsRoleDao;
import com.young.mall.model.UmsRoleEntity;
import com.young.mall.service.UmsRoleService;
import com.young.mall.vo.RoleVo;
import org.springframework.stereotype.Service;

@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleDao, UmsRoleEntity> implements UmsRoleService {


    @Override
    public void creat(RoleVo roleVo) {
        String roleName = roleVo.getName();

        int count = this.count(new LambdaQueryWrapper<UmsRoleEntity>()
                .eq(UmsRoleEntity::getName, roleName));

        if (count > 0) {
            throw new ApiException("已经存在该角色名称");
        }

        UmsRoleEntity umsRoleEntity = new UmsRoleEntity();
        umsRoleEntity.setName(roleVo.getName());
        umsRoleEntity.setDescription(roleVo.getDescription());
        umsRoleEntity.setAdminCount(0);
        umsRoleEntity.setCreateTime(LocalDateTime.now());
        umsRoleEntity.setStatus(roleVo.getStatus());
        umsRoleEntity.setSort(0);

        this.save(umsRoleEntity);

    }
}
