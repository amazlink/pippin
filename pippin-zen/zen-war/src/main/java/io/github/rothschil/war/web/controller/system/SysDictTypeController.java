package io.github.rothschil.war.web.controller.system;

import io.github.rothschil.war.core.domain.SysDictType;
import io.github.rothschil.war.core.service.ISysDictTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.github.rothschil.base.aop.annotation.ApplicationLog;
import io.github.rothschil.common.core.controller.BaseController;
import io.github.rothschil.common.core.domain.AjaxResult;
import io.github.rothschil.common.core.domain.Ztree;
import io.github.rothschil.common.core.page.TableDataInfo;
import io.github.rothschil.common.enums.BusinessType;
import io.github.rothschil.common.utils.ShiroUtils;
import io.github.rothschil.common.utils.poi.ExcelUtil;
import io.github.rothschil.war.constant.UserConstants;

import java.util.List;


/**
 * 数据字典信息
 *
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2019/10/9 - 20:59
 * @since 1.0.0
 */
@Controller
@RequestMapping("/system/dict")
public class SysDictTypeController extends BaseController {

    private final String prefix = "system/dict/type";

    @Autowired
    private ISysDictTypeService dictTypeService;

    @RequiresPermissions("system:dict:view")
    @GetMapping()
    public String dictType() {
        return prefix + "/type";
    }

    @PostMapping("/list")
    @RequiresPermissions("system:dict:list")
    @ResponseBody
    public TableDataInfo list(SysDictType dictType) {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    @ApplicationLog(title = "字典类型", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:dict:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysDictType dictType) {

        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        ExcelUtil<SysDictType> util = new ExcelUtil<>(SysDictType.class);
        return util.exportExcel(list, "字典类型");
    }

    /**
     * 新增字典类型
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存字典类型
     */
    @ApplicationLog(title = "字典类型", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:dict:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysDictType dict) {
        if (UserConstants.DICT_TYPE_NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(dictTypeService.insertDictType(dict));
    }

    /**
     * 修改字典类型
     */
    @GetMapping("/edit/{dictId}")
    public String edit(@PathVariable("dictId") Long dictId, ModelMap mmap) {
        mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
        return prefix + "/edit";
    }

    /**
     * 修改保存字典类型
     */
    @ApplicationLog(title = "字典类型", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:dict:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysDictType dict) {
        if (UserConstants.DICT_TYPE_NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        dict.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(dictTypeService.updateDictType(dict));
    }

    @ApplicationLog(title = "字典类型", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dict:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dictTypeService.deleteDictTypeByIds(ids));
    }

    /**
     * 清空缓存
     */
    @RequiresPermissions("system:dict:remove")
    @ApplicationLog(title = "字典类型", businessType = BusinessType.CLEAN)
    @GetMapping("/clearCache")
    @ResponseBody
    public AjaxResult clearCache() {
        dictTypeService.clearCache();
        return success();
    }

    /**
     * 查询字典详细
     */
    @RequiresPermissions("system:dict:list")
    @GetMapping("/detail/{dictId}")
    public String detail(@PathVariable("dictId") Long dictId, ModelMap mmap) {
        mmap.put("dict", dictTypeService.selectDictTypeById(dictId));
        mmap.put("dictList", dictTypeService.selectDictTypeAll());
        return "system/dict/data/data";
    }

    /**
     * 校验字典类型
     */
    @PostMapping("/checkDictTypeUnique")
    @ResponseBody
    public String checkDictTypeUnique(SysDictType dictType) {
        return dictTypeService.checkDictTypeUnique(dictType);
    }

    /**
     * 选择字典树
     */
    @GetMapping("/selectDictTree/{columnId}/{dictType}")
    public String selectDeptTree(@PathVariable("columnId") Long columnId, @PathVariable("dictType") String dictType,
                                 ModelMap mmap) {
        mmap.put("columnId", columnId);
        mmap.put("dict", dictTypeService.selectDictTypeByType(dictType));
        return prefix + "/tree";
    }

    /**
     * 加载字典列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData() {
        return dictTypeService.selectDictTree(new SysDictType());
    }
}
