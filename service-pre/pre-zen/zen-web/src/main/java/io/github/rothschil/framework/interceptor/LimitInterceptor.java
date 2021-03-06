package io.github.rothschil.framework.interceptor;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import io.github.rothschil.base.response.enums.Status;
import io.github.rothschil.common.exception.DrunkardException;
import io.github.rothschil.base.response.po.ErR;
import io.github.rothschil.common.annotation.RequestLimit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * 拦截器，用于单位时间内限制请求访问的次数
 *
 * @author WCNGS@QQ.COM
 * @date 20/10/30 22:08
 * @since 1.0.0
 */
@SuppressWarnings({"unused","unchecked"})
@Slf4j
@Component
public class LimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * isAssignableFrom() 判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口
         * isAssignableFrom()方法是判断是否为某个类的父类
         * instanceof关键字是判断是否某个类的子类
         */
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final RequestLimit classAnnotation = handlerMethod.getBeanType().getAnnotation(RequestLimit.class);
            final RequestLimit methodAnnotation = handlerMethod.getMethod().getAnnotation(RequestLimit.class);

            RequestLimit requestLimit = methodAnnotation != null ? methodAnnotation : classAnnotation;
            if (requestLimit != null) {
                if (isLimit(request, requestLimit)) {
                    resonseOut(response, ErR.fail(Status.API_REQ_MORE_THAN_SET, new DrunkardException(Status.API_REQ_MORE_THAN_SET)));
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 回写给客户端
     *
     * @param response 响应
     * @param result   结果
     * @throws IOException IO写入异常
     */
    private void resonseOut(HttpServletResponse response, ErR result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.toJSON(result).toString();
        PrintWriter out = response.getWriter();
        out.append(json);
    }

    /**
     * 判断请求是否受限
     *
     * @param request      请求
     * @param requestLimit Request注解
     * @return boolean
     * @date 20/11/18 09:40
     */
    public boolean isLimit(HttpServletRequest request, RequestLimit requestLimit) {

        // 受限的redis 缓存key ,因为这里用浏览器做测试，我就用sessionid 来做唯一key,如果是app ,可以使用 用户ID 之类的唯一标识。
        String limitKey = request.getServletPath() + request.getSession().getId();
        // 从缓存中获取，当前这个请求访问了几次
        Integer redisCount = (Integer) redisTemplate.opsForValue().get(limitKey);
        if (redisCount == null) {
            //初始 次数
            redisTemplate.opsForValue().set(limitKey, 1, requestLimit.second(), TimeUnit.SECONDS);
        } else {
            if (redisCount >= requestLimit.maxCount()) {
                return true;
            }
            // 次数自增
            redisTemplate.opsForValue().increment(limitKey, 1);
        }
        return false;
    }
}
