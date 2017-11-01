package com.didispace;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

// 开启Spring Cloud Feign的支持
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerApplication {

// Feign客户端默认的Logger.Level对象定义为NONE级别，需要调整级别
//	@Bean
//	Logger.Level feignLoggerLevel() {
//		return Logger.Level.FULL;
//	}

/**
	* Feign的Logger级别
	* NONE: 不记录任何消息
	* BASIC:仅记录请求方法、URL以及响应状态码和执行时间
	* HEADERS: 除了BASIC级别的消息外，记录请求和响应头信息
	* FULL: 记录所有请求和响应细节：包括头信息、请求体、元数据
	*/

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}
