package com.example.test.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration		//스프링 빈으로 등록
public class WebMvcConfig implements WebMvcConfigurer {
	private final long MAX_AGE_SECS =3600;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//모든 경로에 대해
		registry.addMapping("/**")
			//Origin이 http://localhost:5173에 대해
		.allowedOrigins("http://localhost:5173", "http://prod-todo-frontend.ap-northeast-2.elasticbeanstalk.com/") //기존
	//	.allowedOrigins("http://localhost:5173", "http://app.wookydev.com/", "https://app.wookydev.com/") //수정
			// GET, POST, PUT, PATCH, DELETE, OPTION 메서드를 활용한다
		.allowedMethods("GET","POST","PUT","PATCH","DELETE","OPTIONS")
		.allowedHeaders("*")
		.allowCredentials(true)
		.maxAge(MAX_AGE_SECS);
	}
}
