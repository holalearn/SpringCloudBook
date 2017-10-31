package com.didispace.web;

import org.springframework.cloud.netflix.feign.FeignClient;

// 与服务端一样，继承共同接口HelloService
// 通过@FeignClient绑定服务
@FeignClient(value = "HELLO-SERVICE")
public interface RefactorHelloService extends com.didispace.service.HelloService {
    /**
     * 优点：将接口的定义从controller中剥离出来，通过Maven私有仓库可以实现代码共享
     * 缺点：在构建期建立了对接口的依赖，接口的变更会对项目的构建造成影响
     *
}
