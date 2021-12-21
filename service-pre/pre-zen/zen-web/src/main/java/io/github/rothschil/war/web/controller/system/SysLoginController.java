package io.github.rothschil.war.web.controller.system;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.github.rothschil.common.utils.ServletUtils;
import io.github.rothschil.common.utils.StringUtils;
import io.github.rothschil.common.core.controller.BaseController;
import io.github.rothschil.common.core.domain.AjaxResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 登录验证
 *
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2019/10/9 - 21:09
 * @since 1.0.0
 */
@Slf4j
@Controller
public class SysLoginController extends BaseController {
    protected final Logger LOG = LoggerFactory.getLogger(BaseController.class);

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request)) {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password) {
        LOG.warn("username={},password={}",username,password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return success();
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "error/unauth";
    }
}
