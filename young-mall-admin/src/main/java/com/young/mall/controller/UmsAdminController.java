package com.young.mall.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.young.mall.common.CommonPage;
import com.young.mall.common.CommonResult;
import com.young.mall.common.exception.ApiException;
import com.young.mall.model.UmsAdminEntity;
import com.young.mall.model.UmsAdminRoleRelationEntity;
import com.young.mall.model.UmsRoleEntity;
import com.young.mall.service.UmsAdminRoleRelationService;
import com.young.mall.service.UmsAdminService;
import com.young.mall.service.UmsRoleService;
import com.young.mall.vo.UserRegisterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(tags = "UmsAdminController")
@Tag(name = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private UmsAdminRoleRelationService umsAdminRoleRelationService;
    @Autowired
    private UmsRoleService umsRoleService;

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping("list")
    public CommonResult<CommonPage<UmsAdminEntity>> list(@RequestParam(defaultValue = "1") long pageNum,
                                                         @RequestParam(defaultValue = "5") long pageSize,
                                                         @RequestParam(required = false) String keyword) {
        Page<UmsAdminEntity> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<UmsAdminEntity> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(keyword)) {
            wrapper.like(UmsAdminEntity::getUsername, keyword)
                    .or()
                    .like(UmsAdminEntity::getNickName, keyword)
                    .or()
                    .like(UmsAdminEntity::getEmail, keyword);
        }

        Page<UmsAdminEntity> umsAdminEntityPage = umsAdminService.page(page, wrapper);

        return CommonResult.success(CommonPage.restPage(umsAdminEntityPage));
    }

    @ApiOperation("用户注册")
    @PostMapping("register")
    public CommonResult<UmsAdminEntity> register(@RequestBody @Validated UserRegisterVo userRegisterVo) {
        UmsAdminEntity umsAdminEntity = umsAdminService.register(userRegisterVo);

        return CommonResult.success(umsAdminEntity);
    }

    @ApiOperation("修改是否启用状态")
    @PostMapping("updateStatus/{id}")
    public CommonResult<Integer> updateStatus(@PathVariable("id") Long id, Integer status) {
        LambdaUpdateChainWrapper<UmsAdminEntity> wrapper = umsAdminService.lambdaUpdate()
                .set(status != null, UmsAdminEntity::getStatus, status)
                .eq(UmsAdminEntity::getId, id);

        umsAdminService.update(wrapper);

        return CommonResult.success(1);
    }

    @ApiOperation("更新用户")
    @PostMapping("update/{id}")
    public CommonResult<Integer> update(@PathVariable("id") Long id, @Validated UserRegisterVo userRegisterVo) {

        String username = userRegisterVo.getUsername();

        int count = umsAdminService.count(new LambdaQueryWrapper<UmsAdminEntity>()
                .eq(UmsAdminEntity::getUsername, username)
                .ne(UmsAdminEntity::getId, id));

        if (count > 0) {
            throw new ApiException("该用户名已经存在");
        }

        LambdaUpdateChainWrapper<UmsAdminEntity> wrapper = umsAdminService.lambdaUpdate()
                .set(UmsAdminEntity::getUsername, username)
                .set(StringUtils.isNotBlank(userRegisterVo.getNickName()), UmsAdminEntity::getNickName, userRegisterVo.getNickName())
                .set(StringUtils.isNotBlank(userRegisterVo.getEmail()), UmsAdminEntity::getEmail, userRegisterVo.getEmail())
                .set(UmsAdminEntity::getPassword, userRegisterVo.getPassword())
                .set(StringUtils.isNotBlank(userRegisterVo.getNote()), UmsAdminEntity::getNote, userRegisterVo.getNote())
                .set(userRegisterVo.getStatus() != null, UmsAdminEntity::getStatus, userRegisterVo.getStatus())
                .eq(UmsAdminEntity::getId, id);

        umsAdminService.update(wrapper);

        return CommonResult.success(1);
    }

    @ApiOperation("删除用户")
    @PostMapping("delete/{id}")
    public CommonResult<Integer> delete(@PathVariable("id") Long id) {
        umsAdminService.removeById(id);

        return CommonResult.success(1);
    }

    @ApiOperation("已经拥有的角色列表")
    @GetMapping("role/{id}")
    public CommonResult<List<UmsRoleEntity>> roleListByAdmin(@PathVariable("id") Integer id) {
        List<Long> roleIds = umsAdminRoleRelationService.list(new LambdaQueryWrapper<UmsAdminRoleRelationEntity>().eq(UmsAdminRoleRelationEntity::getAdminId, id))
                .stream()
                .map(UmsAdminRoleRelationEntity::getRoleId)
                .distinct()
                .collect(Collectors.toList());


        List<UmsRoleEntity> umsRoleEntities = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(roleIds)) {
            umsRoleEntities = umsRoleService.listByIds(roleIds);
        }

        return CommonResult.success(umsRoleEntities);
    }

    @ApiOperation("更新角色")
    @GetMapping("role/update")
    public CommonResult<Integer> updateRole(Long adminId, List<Long> roleIds) {
        int size = umsAdminRoleRelationService.updateRole(adminId, roleIds);
        return CommonResult.success(size);
    }
}
