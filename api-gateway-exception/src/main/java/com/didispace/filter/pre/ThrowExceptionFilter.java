package com.didispace.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
/**
 * 过滤器包含4个基本特征
 * 1. 过滤类型
 * 2. 执行顺序
 * 3. 执行条件
 * 4. 具体操作
 */
public class ThrowExceptionFilter extends ZuulFilter  {

    private static Logger log = LoggerFactory.getLogger(ThrowExceptionFilter.class);

    @Override
    /**
     * 有四种不同生命周期的过滤器
     * 1. pre：在请求被路由之前调用
     * 2. routing：在请求被路由时调用
     * 3. error：处理请求发生错误时调用
     * 4. post: 在routing和error过滤器之后调用
     */
    public String filterType() {
        return "pre";
    }

    @Override
    // 通过返回值指定调用顺序，数值越小优先级越高
    public int filterOrder() {
        return 0;
    }

    @Override
    // 判断过滤器是否要执行，可以通过此方法限定过滤器范围
    public boolean shouldFilter() {
        return true;
    }

    @Override
    // 过滤的具体逻辑
    public Object run() {
        log.info("This is a pre filter, it will throw a RuntimeException");
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            doSomething();
        } catch (Exception e) {
            ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            ctx.set("error.exception", e);
//            ctx.set("error.message", "有一些错误发生");
        }
        return null;
    }

    private void doSomething() {
        throw new RuntimeException("Exist some errors...");
    }

}
