package com.young.mall.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.young.mall.common.CommonPage;
import com.young.mall.common.CommonResult;
import com.young.mall.common.exception.ApiException;
import com.young.mall.model.UmsResourceEntity;
import com.young.mall.service.UmsResourceService;
import com.young.mall.vo.UmsResourceVo;
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

@RestController
@RequestMapping("/resource")
public class UmsResourceController {

    @Autowired
    private UmsResourceService umsResourceService;

    @GetMapping("/list")
    public CommonResult<CommonPage<UmsResourceEntity>> list(@RequestParam(defaultValue = "1") Long pageNum,
                                                            @RequestParam(defaultValue = "5") Long pageSize,
                                                            @RequestParam(required = false) String nameKeyword,
                                                            @RequestParam(required = false) String urlKeyword,
                                                            @RequestParam(required = false) Long categoryId) {

        Page<UmsResourceEntity> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<UmsResourceEntity> wrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotBlank(nameKeyword)) {
            wrapper.like(UmsResourceEntity::getName, nameKeyword);
        }

        if (StringUtils.isNotBlank(urlKeyword)) {
            wrapper.like(UmsResourceEntity::getUrl, urlKeyword);
        }

        if (categoryId != null) {
            wrapper.eq(UmsResourceEntity::getCategoryId, categoryId);
        }


        Page<UmsResourceEntity> umsResourceEntityPage = umsResourceService.page(page, wrapper);

        return CommonResult.success(CommonPage.restPage(umsResourceEntityPage));

    }

    @GetMapping("/listAll")
    public CommonResult<List<UmsResourceEntity>> listAll() {

        List<UmsResourceEntity> list = umsResourceService.list();

        return CommonResult.success(list);

    }

    @PostMapping("/create")
    public CommonResult<Integer> create(@Validated @RequestBody UmsResourceVo umsResourceVo) {
        String name = umsResourceVo.getName();
        int count = umsResourceService.count(new LambdaQueryWrapper<UmsResourceEntity>()
                .eq(UmsResourceEntity::getName, name));

        if (count > 0) {
            throw new ApiException("该资源名称已经存在");
        }

        UmsResourceEntity umsResourceEntity = new UmsResourceEntity();
        umsResourceEntity.setCreateTime(LocalDateTime.now());
        umsResourceEntity.setName(umsResourceVo.getName());
        umsResourceEntity.setUrl(umsResourceVo.getUrl());
        umsResourceEntity.setDescription(umsResourceVo.getDescription());
        umsResourceEntity.setCategoryId(umsResourceVo.getCategoryId());

        umsResourceService.save(umsResourceEntity);

        return CommonResult.success(1);
    }

    @PostMapping("/update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, @Validated @RequestBody UmsResourceVo umsResourceVo) {

        String name = umsResourceVo.getName();
        int count = umsResourceService.count(new LambdaQueryWrapper<UmsResourceEntity>()
                .eq(UmsResourceEntity::getName, name)
                .ne(UmsResourceEntity::getId, id));

        if (count > 0) {
            throw new ApiException("该资源名称已经存在");
        }
        LambdaUpdateChainWrapper<UmsResourceEntity> wrapper = umsResourceService.lambdaUpdate()
                .set(UmsResourceEntity::getName, umsResourceVo.getName())
                .set(UmsResourceEntity::getCategoryId, umsResourceVo.getCategoryId())
                .set(UmsResourceEntity::getUrl, umsResourceVo.getUrl())
                .set(StringUtils.isNotBlank(umsResourceVo.getDescription()), UmsResourceEntity::getDescription, umsResourceVo.getDescription())
                .eq(UmsResourceEntity::getId, umsResourceVo.getId());

        umsResourceService.update(wrapper);

        return CommonResult.success(1);
    }

    @PostMapping("/delete/{id}")
    public CommonResult<Integer> delete(@PathVariable Long id) {

        umsResourceService.removeById(id);

        return CommonResult.success(1);
    }
}
