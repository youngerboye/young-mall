package com.young.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.young.mall.common.exception.ApiException;
import com.young.mall.dao.UmsAdminDao;
import com.young.mall.model.UmsAdminEntity;
import com.young.mall.service.UmsAdminService;
import com.young.mall.vo.UserRegisterVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminDao, UmsAdminEntity> implements UmsAdminService {

    @Override
    public UmsAdminEntity register(UserRegisterVo userRegisterVo) {
        String username = userRegisterVo.getUsername();
        int count = this.count(new LambdaQueryWrapper<UmsAdminEntity>().eq(UmsAdminEntity::getUsername, username));
        if (count > 0) {
            throw new ApiException("用户已经存在");
        }
        UmsAdminEntity umsAdminEntity = new UmsAdminEntity();
        BeanUtils.copyProperties(userRegisterVo, umsAdminEntity);
        umsAdminEntity.setCreateTime(LocalDateTime.now());

        this.save(umsAdminEntity);
        return umsAdminEntity;
    }
}
