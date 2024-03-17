package com.young.mall.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.young.mall.common.CommonPage;
import com.young.mall.common.CommonResult;
import com.young.mall.common.exception.ApiException;
import com.young.mall.model.UmsMenuEntity;
import com.young.mall.service.UmsMenuService;
import com.young.mall.vo.MenuTreeVo;
import com.young.mall.vo.MenuVo;
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

@RestController
@RequestMapping("menu")
public class UmsMenuController {

    @Autowired
    private UmsMenuService umsMenuService;

    @GetMapping("list/{id}")
    public CommonResult<CommonPage<UmsMenuEntity>> list(@PathVariable("id") Long id,
                                                        @RequestParam("pageNum") Long pageNum,
                                                        @RequestParam("pageSize") Long pageSize) {
        Page<UmsMenuEntity> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<UmsMenuEntity> wrapper = new LambdaQueryWrapper<UmsMenuEntity>()
                .eq(UmsMenuEntity::getParentId, id);

        Page<UmsMenuEntity> umsMenuEntityPage = umsMenuService.page(page, wrapper);

        return CommonResult.success(CommonPage.restPage(umsMenuEntityPage));
    }

    @PostMapping("/create")
    public CommonResult<Integer> create(@Validated @RequestBody MenuVo menuVo) {

        String name = menuVo.getName();
        int count = umsMenuService.count(new LambdaQueryWrapper<UmsMenuEntity>()
                .eq(UmsMenuEntity::getName, name)
                .eq(UmsMenuEntity::getParentId, menuVo.getParentId()));
        if (count > 0) {
            throw new ApiException("该层级菜单下已经存在该菜单名称");
        }
        UmsMenuEntity umsMenuEntity = new UmsMenuEntity();
        BeanUtils.copyProperties(menuVo, umsMenuEntity);
        umsMenuEntity.setCreateTime(LocalDateTime.now());
        umsMenuEntity.setLevel(menuVo.getParentId() == 0 ? 0 : 1);

        umsMenuService.save(umsMenuEntity);

        return CommonResult.success(1);
    }

    @GetMapping("/{id}")
    public CommonResult<UmsMenuEntity> info(@PathVariable Long id) {
        UmsMenuEntity byId = umsMenuService.getById(id);

        return CommonResult.success(byId);
    }

    @PostMapping("update/{id}")
    public CommonResult<Integer> update(@PathVariable Long id, MenuVo menuVo) {
        String name = menuVo.getName();
        int count = umsMenuService.count(new LambdaQueryWrapper<UmsMenuEntity>()
                .eq(UmsMenuEntity::getName, name)
                .eq(UmsMenuEntity::getParentId, menuVo.getParentId())
                .ne(UmsMenuEntity::getId, id));
        if (count > 0) {
            throw new ApiException("该层级菜单下已经存在该菜单名称");
        }
        LambdaUpdateWrapper<UmsMenuEntity> wrapper = new LambdaUpdateWrapper<UmsMenuEntity>()
                .set(UmsMenuEntity::getName, menuVo.getName())
                .set(UmsMenuEntity::getParentId, menuVo.getParentId())
                .set(UmsMenuEntity::getTitle, menuVo.getTitle())
                .set(UmsMenuEntity::getIcon, menuVo.getIcon())
                .set(UmsMenuEntity::getHidden, menuVo.getHidden())
                .set(menuVo.getSort() != null, UmsMenuEntity::getSort, menuVo.getSort())
                .eq(UmsMenuEntity::getId, id);

        umsMenuService.update(wrapper);

        return CommonResult.success(1);
    }

    @PostMapping("delete/{id}")
    public CommonResult<Integer> delete(@PathVariable Long id) {
        int count = umsMenuService.count(new LambdaQueryWrapper<UmsMenuEntity>()
                .eq(UmsMenuEntity::getParentId, id));

        if (count > 0) {
            throw new ApiException("该菜单下面有子菜单，不可删除");
        }

        umsMenuService.removeById(id);

        return CommonResult.success(1);

    }

    @GetMapping("/treeList")
    public CommonResult<List<MenuTreeVo>> treeList() {

        List<MenuTreeVo> menuTreeAllVoList = umsMenuService.list()
                .stream()
                .map(e -> {
                    MenuTreeVo menuTreeVo = new MenuTreeVo();
                    BeanUtils.copyProperties(e, menuTreeVo);
                    return menuTreeVo;
                }).collect(Collectors.toList());

        List<MenuTreeVo> collect = menuTreeAllVoList.stream().filter(e -> e.getParentId().equals(0L))
                .peek(e -> e.setChildren(generatorTree(e, menuTreeAllVoList)))
                .collect(Collectors.toList());

        return CommonResult.success(collect);
    }

    private List<MenuTreeVo> generatorTree(MenuTreeVo menuTreeParentVo, List<MenuTreeVo> menuTreeAllVoList) {
        return menuTreeAllVoList.stream().filter(e -> e.getParentId().equals(menuTreeParentVo.getId()))
                .peek(e -> e.setChildren(generatorTree(e, menuTreeAllVoList)))
                .collect(Collectors.toList());
    }
}
