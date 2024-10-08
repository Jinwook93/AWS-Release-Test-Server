package com.example.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RestController는 Spring Framework에서 제공하는 어노테이션으로, 주로 웹 애플리케이션에서 RESTful 웹 서비스를 구현할 때 사용됩니다.
//이 어노테이션은 @Controller와 @ResponseBody 어노테이션을 결합한 것으로, 주로 JSON 형식의 데이터를 반환하는 API를 개발할 때 사용됩니다.
public class HealthController {

	@GetMapping("/")
	public String healthCheck() {
		return "The Service is up and running...";
	}
}
