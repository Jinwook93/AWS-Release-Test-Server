package com.example.test.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.dto.ResponseDTO;
import com.example.test.dto.TestRequestBodyDTO;
import com.example.test.dto.TodoDTO;
import com.example.test.dto.UserDTO;
import com.example.test.model.TodoEntity;
import com.example.test.model.UserEntity;
import com.example.test.security.TokenProvider;
import com.example.test.service.TodoService;
import com.example.test.service.UserService;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

	@Autowired
	private UserService userService;


	@Autowired
	private TokenProvider tokenProvider;
	
	//Bean으로 작성해도 됨
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {

		try {
			
				//요청을 이용해 저장할 사용자 만들기
			UserEntity user = UserEntity.builder().email(userDTO.getEmail())
					.username(userDTO.getUsername())
					.password(passwordEncoder.encode(userDTO.getPassword()))
					.build();
			
			// 서비스를 이용해 리포지터리에 사용자 저장
			UserEntity registeredUser = userService.create(user);
			UserDTO responseUserDTO = UserDTO.builder()
					.email(registeredUser.getEmail())
					.id(registeredUser.getId())
					.username(registeredUser.getUsername())
					.build();
			
			//사용자 정보는 항상 하나이므로 리스트로 만들어야 하는 ResponseDTO를 사용하지 않고 그냥 UserDTO 리턴
			return ResponseEntity.ok().body(responseUserDTO);
		}catch (Exception e) {
			//예외가 나는 경우 bad 리스폰스 리턴
			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
			return ResponseEntity
					.badRequest()
					.body(responseDTO);
			
		}
		
			
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
		
		
		
	
		UserEntity user = userService.getByCredentials(userDTO.getEmail(), userDTO.getPassword(),passwordEncoder);
		
		
				
		if(user != null) {
			//토큰 생성
			
			final String token = tokenProvider.create(user);
			final UserDTO responseUserDTO = UserDTO.builder()
					.email(user.getEmail())
					.id(user.getId())
					.token(token)
					.build();
//			final UserDTO responseUserDTO = UserDTO.builder()
//					.email(user.getEmail())
//					.id(user.getId())
//					.build();
			return ResponseEntity.ok().body(responseUserDTO);
			
		}
		
		else {
			
			ResponseDTO responseDTO = ResponseDTO.builder().error("Login Failed").build();
			return ResponseEntity
					.badRequest()
					.body(responseDTO);
		}
	
			
	}
	

}		
