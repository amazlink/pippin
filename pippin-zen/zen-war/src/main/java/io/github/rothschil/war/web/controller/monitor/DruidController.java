package io.github.rothschil.war.web.controller.monitor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import io.github.rothschil.common.core.controller.BaseController;

/**
 * druid 监控
 *
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2019/10/9 - 20:55
 * @since 1.0.0
 */
@Controller
@RequestMapping("/monitor/data")
public class DruidController extends BaseController {

    @RequiresPermissions("monitor:data:view")
    @GetMapping()
    public String index() {
        String prefix = "/druid";
        return redirect(prefix + "/index");
    }
}
