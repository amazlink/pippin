package io.github.rothschil.war.web.controller.monitor;

import io.github.rothschil.war.core.domain.SysLogininfor;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.github.rothschil.common.core.controller.BaseController;
import io.github.rothschil.common.core.domain.AjaxResult;
import io.github.rothschil.common.core.page.TableDataInfo;
import io.github.rothschil.common.utils.poi.ExcelUtil;
import io.github.rothschil.framework.shiro.service.SysPasswordService;
import io.github.rothschil.war.core.service.ISysLogininforService;

import java.util.List;


/** 系统访问记录
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2019/10/9 - 20:56
 * @since 1.0.0
 */
@Controller
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController {

    @Autowired
    private ISysLogininforService logininforService;

    @Autowired
    private SysPasswordService passwordService;

    @RequiresPermissions("monitor:logininfor:view")
    @GetMapping()
    public String logininfor() {
        String prefix = "monitor/logininfor";
        return prefix + "/logininfor";
    }

    @RequiresPermissions("monitor:logininfor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysLogininfor logininfor) {
        startPage();
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        return getDataTable(list);
    }

    @RequiresPermissions("monitor:logininfor:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysLogininfor logininfor) {
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        ExcelUtil<SysLogininfor> util = new ExcelUtil<>(SysLogininfor.class);
        return util.exportExcel(list, "登陆日志");
    }

    @RequiresPermissions("monitor:logininfor:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(logininforService.deleteLogininforByIds(ids));
    }

    @RequiresPermissions("monitor:logininfor:remove")
    @PostMapping("/clean")
    @ResponseBody
    public AjaxResult clean() {
        logininforService.cleanLogininfor();
        return success();
    }

    @RequiresPermissions("monitor:logininfor:unlock")
    @PostMapping("/unlock")
    @ResponseBody
    public AjaxResult unlock(String loginName) {
        passwordService.clearLoginRecordCache(loginName);
        return success();
    }
}
