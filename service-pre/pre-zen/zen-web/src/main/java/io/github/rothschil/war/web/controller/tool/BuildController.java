package io.github.rothschil.war.web.controller.tool;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import io.github.rothschil.common.core.controller.BaseController;

/**
 * build 表单构建
 *
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2019/10/9 - 21:10
 * @since 1.0.0
 */
@Controller
@RequestMapping("/tool/build")
public class BuildController extends BaseController {

    @RequiresPermissions("tool:build:view")
    @GetMapping()
    public String build() {
        String prefix = "tool/build";
        return prefix + "/build";
    }
}
