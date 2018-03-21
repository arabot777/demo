package com.mmall.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/")
	public String home(){
		return "hello spring boot";
	}

	@RequestMapping("/hello")
	public String hello(){
		return "hello world";
	}


	@PreAuthorize("hasRole('ROLE_admin')")
	@RequestMapping("/roleAuth")
	public String role(Integer id,String username,User user){
		return "admin auth";
	}
	//返回值时偶数
	@PreAuthorize("#id<10 or principal.username.equals(#username) or user.username.equals('abc')")
	@PostAuthorize("returnObject%2==0")
	@RequestMapping("/test")
	public Integer test(Integer id,String username,User user){
		return id;
	}

	//能被4整出，才能返回
	@PreFilter("filterObject%2==0")
	@PostFilter("filterObject%4==0")
	@RequestMapping("/test2")
	public List<Integer> test2(List<Integer> idList){
		return idList;
	}
}
