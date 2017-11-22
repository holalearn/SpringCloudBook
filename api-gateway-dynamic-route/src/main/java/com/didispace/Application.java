package com.didispace;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringCloudApplication
// 动态路由配置信息加载
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

	@Bean
	// 步骤三：使用@RefreshScope注解使Zuul的配置内容动态化
	// 完成配置之后，还需要在Git仓库中增加网关配置文件api-gateway。properties，文件名取决于bootstrap.properties中spring.application.nam的属性值
	// GET /routes接口可以获取当前网关的路由规则
	// POST /refresh接口刷新配置信息
	@RefreshScope
	@ConfigurationProperties("zuul")
	public ZuulProperties zuulProperties() {
		return new ZuulProperties();
	}

}
