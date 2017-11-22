package com.didispace;

import com.didispace.filter.AccessFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

// 步骤二：创建主类，使用@EnableZuulProxy注解开启Zuul的API网关服务
@EnableZuulProxy
@SpringCloudApplication
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}

	@Bean
	// 创建自定义过滤器AccessFilter的bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}

	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
		return new PatternServiceRouteMapper(
				// 目的：不同版本的微服务应用生成以版本号为路由前缀定义的路由规则
				// 向后引用：使用小括号指定一个子表达式后，匹配这个子表达式的文本
				// (?<name>exp)	匹配exp,并捕获文本到名称为name的组里
				// (?<name>^.+) ^.+ 匹配除换行符以外的任意字符，重复一次或更多次，并把组名指定为name
				// (?<version>v.+$) v.+$ 匹配开头为v，后面接重复一次或更多次除换行符以外的任意字符，并把组名指定为version
				// 自动为服务名为userservice-v1的微服务创建/v1/userservice/**的路径匹配规则
				// 如果没有匹配，还会采取完整服务名最为

				"(?<name>^.+)-(?<version>v.+$)",
				"${version}/${name}");
	}

}
