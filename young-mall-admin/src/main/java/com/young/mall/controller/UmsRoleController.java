package com.young.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.young.mall.common.CommonPage;
import com.young.mall.common.CommonResult;
import com.young.mall.common.exception.ApiException;
import com.young.mall.model.UmsMenuEntity;
import com.young.mall.model.UmsResourceEntity;
import com.young.mall.model.UmsRoleEntity;
import com.young.mall.model.UmsRoleMenuRelationEntity;
import com.young.mall.model.UmsRoleResourceRelationEntity;
import com.young.mall.service.UmsMenuService;
import com.young.mall.service.UmsResourceService;
import com.young.mall.service.UmsRoleMenuRelationService;
import com.young.mall.service.UmsRoleResourceRelationService;
import com.young.mall.service.UmsRoleService;
import com.young.mall.vo.RoleVo;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("role")
@Api(tags = "UmsRoleController")
@Tag(name = "UmsRoleController", description = "角色管理")
public class UmsRoleController {

    @Autowired
    private UmsRoleService umsRoleService;

    @Autowired
    private UmsRoleMenuRelationService umsRoleMenuRelationService;

    @Autowired
    private UmsRoleResourceRelationService umsRoleResourceRelationService;

    @Autowired
    private UmsMenuService umsMenuService;

    @Autowired
    private UmsResourceService umsResourceService;

    @GetMapping("list")
    public CommonResult<CommonPage<UmsRoleEntity>> roleList(@RequestParam(value = "pageNum", defaultValue = "1") Long pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Long pageSize,
                                                            @RequestParam(required = false) String keyword) {

        Page<UmsRoleEntity> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<UmsRoleEntity> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(keyword)) {
            wrapper.like(UmsRoleEntity::getName, keyword);
        }

        Page<UmsRoleEntity> roleEntityPage = umsRoleService.page(page, wrapper);

        return CommonResult.success(CommonPage.restPage(roleEntityPage));
    }

    @PostMapping("/create")
    public CommonResult<Integer> create(@Validated @RequestBody RoleVo roleVo) {

        umsRoleService.creat(roleVo);

        return CommonResult.success(1);
    }

    @PostMapping("update/{id}")
    public CommonResult<Integer> update(@PathVariable("id") Long id, @Validated RoleVo roleVo) {

        String roleName = roleVo.getName();

        int count = umsRoleService.count(new LambdaQueryWrapper<UmsRoleEntity>()
                .eq(UmsRoleEntity::getName, roleName)
                .ne(UmsRoleEntity::getId, id));
        if (count > 0) {
            throw new ApiException("已经存在该角色名称");
        }
        umsRoleService.update(new LambdaUpdateWrapper<UmsRoleEntity>()
                .set(UmsRoleEntity::getName, roleName)
                .set(StringUtils.isNotBlank(roleVo.getDescription()), UmsRoleEntity::getDescription, roleVo.getDescription())
                .set(UmsRoleEntity::getStatus, roleVo.getStatus())
                .eq(UmsRoleEntity::getId, id));

        return CommonResult.success(1);
    }

    @PostMapping("updateStatus/{id}")
    public CommonResult<Integer> updateStatus(@PathVariable("id") Long id, Integer status) {
        umsRoleService.update(new LambdaUpdateWrapper<UmsRoleEntity>()
                .set(UmsRoleEntity::getStatus, status)
                .eq(UmsRoleEntity::getId, id));

        return CommonResult.success(1);
    }

    @PostMapping("delete")
    public CommonResult<Integer> delete(Long ids) {
        umsRoleService.removeById(ids);

        return CommonResult.success(1);
    }

    @PostMapping("/allocMenu")
    public CommonResult<Integer> allocMenu(Long roleId, List<Long> menuIds) {

        Integer size = umsRoleMenuRelationService.allocMenu(roleId, menuIds);

        return CommonResult.success(size);
    }

    @PostMapping("/allocResource")
    public CommonResult<Integer> allocResource(Long roleId, List<Long> resourceIds) {

        Integer size = umsRoleResourceRelationService.allocResource(roleId, resourceIds);

        return CommonResult.success(size);
    }

    @GetMapping("/listMenu/{id}")
    public CommonResult<List<UmsMenuEntity>> listMenu(@PathVariable Long id) {
        List<Long> menuIds = umsRoleMenuRelationService.list(new LambdaQueryWrapper<UmsRoleMenuRelationEntity>()
                .eq(UmsRoleMenuRelationEntity::getRoleId, id))
                .stream()
                .map(UmsRoleMenuRelationEntity::getMenuId)
                .distinct()
                .collect(Collectors.toList());

        List<UmsMenuEntity> umsMenuEntities = umsMenuService.listByIds(menuIds);

        return CommonResult.success(umsMenuEntities);
    }

    @GetMapping("/listResource/{id}")
    public CommonResult<List<UmsResourceEntity>> listResource(@PathVariable Long id) {
        List<Long> resourceIds = umsRoleResourceRelationService.list(new LambdaQueryWrapper<UmsRoleResourceRelationEntity>()
                .eq(UmsRoleResourceRelationEntity::getRoleId, id))
                .stream()
                .map(UmsRoleResourceRelationEntity::getResourceId)
                .distinct()
                .collect(Collectors.toList());

        List<UmsResourceEntity> umsResourceEntities = umsResourceService.listByIds(resourceIds);

        return CommonResult.success(umsResourceEntities);
    }

}
