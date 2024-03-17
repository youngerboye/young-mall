package com.young.mall.controller;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.young.mall.common.CommonResult;
import com.young.mall.common.exception.ApiException;
import com.young.mall.model.UmsResourceCategoryEntity;
import com.young.mall.service.UmsResourceCategoryService;
import com.young.mall.vo.UmsResourceCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("resourceCategory")
public class UmsResourceCategoryController {

    @Autowired
    private UmsResourceCategoryService umsResourceCategoryService;

    @GetMapping("listAll")
    public CommonResult<List<UmsResourceCategoryEntity>> listAll() {
        List<UmsResourceCategoryEntity> list = umsResourceCategoryService.list();

        return CommonResult.success(list);
    }

    @PostMapping("create")
    public CommonResult<Integer> create(@Validated @RequestBody UmsResourceCategoryVo umsResourceCategoryVo) {
        String name = umsResourceCategoryVo.getName();

        int count = umsResourceCategoryService.count(new LambdaQueryWrapper<UmsResourceCategoryEntity>()
                .eq(UmsResourceCategoryEntity::getName, name));
        if (count > 0) {
            throw new ApiException("该分类模块名称已经存在");
        }
        UmsResourceCategoryEntity umsResourceCategoryEntity = new UmsResourceCategoryEntity();
        umsResourceCategoryEntity.setCreateTime(LocalDateTime.now());
        umsResourceCategoryEntity.setName(name);
        umsResourceCategoryEntity.setSort(umsResourceCategoryVo.getSort());

        umsResourceCategoryService.save(umsResourceCategoryEntity);

        return CommonResult.success(1);
    }

    @PostMapping("update/{id}")
    public CommonResult<Integer> update(@PathVariable Integer id, @RequestBody UmsResourceCategoryVo umsResourceCategoryVo) {
        int count = umsResourceCategoryService.count(new LambdaQueryWrapper<UmsResourceCategoryEntity>()
                .eq(UmsResourceCategoryEntity::getName, umsResourceCategoryVo.getName())
                .ne(UmsResourceCategoryEntity::getId, id));
        if (count > 0) {
            throw new ApiException("该分类模块名称已经存在");
        }

        umsResourceCategoryService.lambdaUpdate()
                .set(UmsResourceCategoryEntity::getName, umsResourceCategoryVo.getName())
                .set(umsResourceCategoryVo.getSort() != null, UmsResourceCategoryEntity::getSort, umsResourceCategoryVo.getSort())
                .eq(UmsResourceCategoryEntity::getId, id);
        return CommonResult.success(1);
    }

    @PostMapping("/delete/{id}")
    public CommonResult<Integer> delete(@PathVariable Long id) {
        umsResourceCategoryService.removeById(id);

        return CommonResult.success(1);
    }

}
