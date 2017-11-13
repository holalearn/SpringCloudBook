package com.didispace.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

// 问题：微服务的权限
// 解决方案一：将各个微服务逻辑相同的校验逻辑剥离出来，构建出一个独立的鉴权服务，在应用中调用鉴权服务
// 方案一缺点：仅仅将逻辑剥离，每个应用冗余的拦截器或过滤器依旧存在
// 解决方案二：通过前置网关服务完成非业务性质的校验，外部客户端访问微服务时，已经有统一入口，在此完成校验和过滤

// Zuul请求过滤的实现：继承ZuulFilter抽象类并实现 定义的四个方法
public class AccessFilter extends ZuulFilter  {

    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    // 过滤器类型，决定过滤器在请求的哪个生命周期执行
    public String filterType() {
        // 在请求被路由之前执行
        return "pre";
    }

    @Override
    // 过滤器的执行顺序。当一个阶段有多个过滤器时，根据此返回值依次执行
    public int filterOrder() {
        return 0;
    }

    @Override
    // 过滤器的使用范围。这里直接返回true，过滤所有请求。
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());

        Object accessToken = request.getParameter("accessToken");
        if(accessToken == null) {
            log.warn("access token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            return null;
        }
        log.info("access token ok");
        return null;
    }

}
