package com.didispace.web;

import com.didispace.dto.User;
import com.didispace.service.HelloService;
import org.springframework.web.bind.annotation.*;

@RestController
// 实现HelloService，实现服务端、客户端共同定义的接口
public class RefactorHelloController implements HelloService {

	@Override
	// 注意不需要再设置映射注解@RequestMapping
	// 参数注解@RequestParam是重写时自动带入的
	// 因此只需要实现接口逻辑，再添加@RestController就可以将当前类变成REST接口类
	public String hello(@RequestParam("name") String name) {
		return "Hello " + name;
	}

	@Override
	public User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age) {
		return new User(name, age);
	}

	@Override
	public String hello(@RequestBody User user) {
		return "Hello "+ user.getName() + ", " + user.getAge();
	}

}
