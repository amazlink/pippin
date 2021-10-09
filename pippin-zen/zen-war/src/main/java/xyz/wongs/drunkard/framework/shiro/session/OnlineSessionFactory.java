package xyz.wongs.drunkard.framework.shiro.session;

import eu.bitwalker.useragentutils.UserAgent;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;
import org.apache.shiro.web.session.mgt.WebSessionContext;
import org.springframework.stereotype.Component;
import xyz.wongs.drunkard.base.utils.IpUtils;
import xyz.wongs.drunkard.base.utils.ServletUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义sessionFactory会话
 *
 * @author <a href="https://github.com/rothschil">Sam</a>
 * @date 2021/10/9 - 21:35
 * @since 1.0.0
 */
@Component
public class OnlineSessionFactory implements SessionFactory {
    @Override
    public Session createSession(SessionContext initData) {
        OnlineSession session = new OnlineSession();
        if (initData != null && initData instanceof WebSessionContext) {
            WebSessionContext sessionContext = (WebSessionContext) initData;
            HttpServletRequest request = (HttpServletRequest) sessionContext.getServletRequest();
            if (request != null) {
                UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                session.setHost(IpUtils.getIpAddr(request));
                session.setBrowser(browser);
                session.setOs(os);
            }
        }
        return session;
    }
}