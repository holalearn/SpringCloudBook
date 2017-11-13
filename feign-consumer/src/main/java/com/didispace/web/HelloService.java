package com.didispace.web;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

// 通过@FeignClient注解指定服务名来绑定服务
// 服务项目位于hello-service中
// 通过fallback属性配置服务的降级实现类
@FeignClient(name="HELLO-SERVICE", fallback = HelloServiceFallback.class)
public interface HelloService {

    // 使用Spring MVC注解绑定服务提供的某一个REST接口
    @RequestMapping("/hello")
    String hello();

    // 带有Request参数请求
    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    String hello(@RequestParam("name") String name) ;

    // 带有Header信息请求
    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    // 带有RequestBody请求，请求体是一个对象
    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    String hello(@RequestBody User user);

}
