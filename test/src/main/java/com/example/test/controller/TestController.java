package com.example.test.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.dto.ResponseDTO;
import com.example.test.dto.TestRequestBodyDTO;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/testResponseEntity")
  public ResponseEntity<?> testControllerResponseEntity()
  {
			List<String> list = new ArrayList<>();
			list.add("Hello world! I'm ResponseEntity And You Got 400!");
			ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
			//http status를 200으로 설정
			return ResponseEntity.ok().body(response);
     
  }
}

//@RestController
//public class TestController {
//
//	@GetMapping("/testResponseBody")
//    public ResponseDTO<String> testControllerResponseBody()
//    {
//			List<String> list = new ArrayList<>();
//			list.add("Hello world! I'm ResponseDTO");
//			ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
//			return response;
//       
//    }
//}

/*									!!!!!!!!!/RequestBody 연습
@RestController
@RequestMapping("test")
public class TestController {

	
	@GetMapping("/testRequestBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO)
    {
	
        return "아이디 :"+ testRequestBodyDTO.getId()+" 메시지 : "+testRequestBodyDTO.getMessage() ;
    }



}
*/

/*
	!!!!!!!!!!!!!!!!//RequestParam

@RestController
public class TestController {

	
	@GetMapping("/testRequestParam")
    public String testControllerRequestParam(@RequestParam(required = false) int id)
    {
        return "RequestParam ID " + id;
    }



}
*/


/*   !!!!!!!!!!!!//PathVariable 

@RestController
public class TestController {

	
	@GetMapping("/{id}")
    public String testControllerWithPathVariable(@PathVariable int id)
    {
        return "Hello World! ID " + id;
    }



}

*/


/*!!!!!!!!!!!!!!RequestMapping이 있는 경우!!!!!!!!!!!!!

@RestController

public class TestController {

	
	@GetMapping("test2/getMappingTest")					//http://localhost:8080/test2/getMappingTest
	public String testControllerWithPath() {
		return "This is GETMAPPING TEST";
	}
	
	@GetMapping					//http의 메서드가 GET 메서드 일 경우 호출
	public String testController() {
		return "Hello World";
	}
	
	

}

!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/

/* !!!!!!!!!!!!!!RequestMapping이 있는 경우!!!!!!!!!!!!!

@RestController
@RequestMapping("test")	//리소스	http://localhost:8080/test
public class TestController {

	
	@GetMapping("getMappingTest")					//http://localhost:8080/test/getMappingTest
	public String testControllerWithPath() {
		return "This is GETMAPPING TEST";
	}
	
	@GetMapping					//http의 메서드가 GET 메서드 일 경우 호출
	public String testController() {
		return "Hello World";
	}
	
	

}
!!!!!!!!!!!!!!!!!!!!!!!!!!*/
