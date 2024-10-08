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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.dto.ResponseDTO;
import com.example.test.dto.TestRequestBodyDTO;
import com.example.test.dto.TodoDTO;
import com.example.test.model.TodoEntity;
import com.example.test.service.TodoService;

import lombok.Builder;

@RestController
@RequestMapping("/todo")
public class TodoController {			//인증된 사용자를 사용할 수 있도록 수정한 후

	@Autowired
	private TodoService service;

	@GetMapping
	public ResponseEntity<?> retrieveTodoList(@AuthenticationPrincipal String userId) {

//		String temporaryUserId = "temporary-user"; // 임시 아이디

		// (1) 서비스 메서드의 retrieve() 메서드를 사용해 Todo 리스트를 가져온다.
		List<TodoEntity> entities = service.retrieve(userId);
		// (2) 자바 스트림을 통해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		// (3) 변환된 TodoDTO 리스트를 이용해 ResponseDTO 를 초기화한다
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		// (4) ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("test")
	public ResponseEntity<?> todoControllerResponseEntity() {

		String str = service.testService(); // 테스트 서비스 사용
		List<String> list = new ArrayList<>();
		list.add(str); // 서비스 내용 출력
//			list.add("todo1");
//			list.add("todo2");
////			
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		// http status를 200으로 설정
		return ResponseEntity.ok().body(response);
		// return ResponseEntity.badRequest().body(response);

	}

	@PostMapping
	public ResponseEntity<?> createTodo(@AuthenticationPrincipal String userId, @RequestBody TodoDTO dto) {

		try {

//			String temporaryUserId = "temporary-user";

			// (1) TodoEntity로 변환
			TodoEntity entity = TodoDTO.toEntity(dto);

			// (2) id를 null로 초기화. 생성 당시에는 없어야 함
			entity.setId(null);

			// (3) 임시 사용자 아이디 설정.
			// 기존 temporary-user대신 @AuthenticationPrincipal 에서 넘어온 userId로 설정해준다
			entity.setUserId(userId);

			// (4) 서비스를 통해 Todo Entity 생성
			List<TodoEntity> entities = service.create(entity);

			// (5)자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

			// (6) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

			// (7) ResponseDTO를 리턴
			return ResponseEntity.ok().body(response);

		} catch (Exception e) {
			// (8) 예외가 있을 경우 dto 대신 error에 메시지를 넣어서 리턴한다
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}

	}

	@PutMapping
	public ResponseEntity<?> updateTodo(@AuthenticationPrincipal String userId, @RequestBody TodoDTO dto) {
//		String temporaryUserId = "temporary-user"; // temporary user id

		// (1) DTO를 Entity로 변환한다.
		TodoEntity entity = TodoDTO.toEntity(dto);

		// (2) 임시 사용자 아이디를 userId로 설정한다. 
		entity.setUserId(userId);

		// (3) 서비스를 이용해 Entity를 업데이트한다.
		List<TodoEntity> entities = service.update(entity);

		// (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

		// (5) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

		// (6) ResponseDTO를 리턴한다.
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping
	public ResponseEntity<?> DeleteTodo(@AuthenticationPrincipal String userId,@RequestBody TodoDTO dto) {

		try {
//			String temporaryUserId = "temporary-user"; // temporary user id

			// (1) DTO를 Entity로 변환한다.
			TodoEntity entity = TodoDTO.toEntity(dto);

			// (2) 임시 사용자 아이디를 userId로 설정한다
			entity.setUserId(userId);

			// (3) 서비스를 이용해 Entity를 delete한다.
			List<TodoEntity> entities = service.delete(entity);

			// (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

			// (5) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

			// (6) ResponseDTO를 리턴한다.
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			// (7) 예외가 있는 경우 dto 대신 error에 메시지를 넣어서 리턴한다.
			String error = e.getMessage();

			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}

}



//수정 전 TodoController

//package com.example.test.controller;
//
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.test.dto.ResponseDTO;
//import com.example.test.dto.TestRequestBodyDTO;
//import com.example.test.dto.TodoDTO;
//import com.example.test.model.TodoEntity;
//import com.example.test.service.TodoService;
//
//import lombok.Builder;
//
//@RestController
//@RequestMapping("/todo")
//public class TodoController {
//
//	@Autowired
//	private TodoService service;
//
//	@GetMapping
//	public ResponseEntity<?> retrieveTodoList() {
//
//		String temporaryUserId = "temporary-user"; // 임시 아이디
//
//		// (1) 서비스 메서드의 retrieve() 메서드를 사용해 Todo 리스트를 가져온다.
//		List<TodoEntity> entities = service.retrieve(temporaryUserId);
//		// (2) 자바 스트림을 통해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
//		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
//		// (3) 변환된 TodoDTO 리스트를 이용해 ResponseDTO 를 초기화한다
//		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
//		// (4) ResponseDTO를 리턴한다.
//		return ResponseEntity.ok().body(response);
//	}
//
//	@GetMapping("test")
//	public ResponseEntity<?> todoControllerResponseEntity() {
//
//		String str = service.testService(); // 테스트 서비스 사용
//		List<String> list = new ArrayList<>();
//		list.add(str); // 서비스 내용 출력
////			list.add("todo1");
////			list.add("todo2");
//////			
//		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
//		// http status를 200으로 설정
//		return ResponseEntity.ok().body(response);
//		// return ResponseEntity.badRequest().body(response);
//
//	}
//
//	@PostMapping
//	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
//
//		try {
//
//			String temporaryUserId = "temporary-user";
//
//			// (1) TodoEntity로 변환
//			TodoEntity entity = TodoDTO.toEntity(dto);
//
//			// (2) id를 null로 초기화. 생성 당시에는 없어야 함
//			entity.setId(null);
//
//			// (3) 임시 사용자 아이디 설정. 인증과 인가 부분에서 수정할 예정
//			// 지금은 인증과 인가 기능이 없으므로 한 사용자(temporary-user)만 로그인 없이 사용할 수 있는 애플리케이션
//			entity.setUserId(temporaryUserId);
//
//			// (4) 서비스를 통해 Todo Entity 생성
//			List<TodoEntity> entities = service.create(entity);
//
//			// (5)자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환
//			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
//
//			// (6) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
//			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
//
//			// (7) ResponseDTO를 리턴
//			return ResponseEntity.ok().body(response);
//
//		} catch (Exception e) {
//			// (8) 예외가 있을 경우 dto 대신 error에 메시지를 넣어서 리턴한다
//			String error = e.getMessage();
//			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
//			return ResponseEntity.badRequest().body(response);
//		}
//
//	}
//
//	@PutMapping
//	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
//		String temporaryUserId = "temporary-user"; // temporary user id
//
//		// (1) DTO를 Entity로 변환한다.
//		TodoEntity entity = TodoDTO.toEntity(dto);
//
//		// (2) userId를 temporaryUserId로 설정한다. 인증과 인가는 추후 수정할 예정.
//		entity.setUserId(temporaryUserId);
//
//		// (3) 서비스를 이용해 Entity를 업데이트한다.
//		List<TodoEntity> entities = service.update(entity);
//
//		// (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
//		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
//
//		// (5) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
//		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
//
//		// (6) ResponseDTO를 리턴한다.
//		return ResponseEntity.ok().body(response);
//	}
//
//	@DeleteMapping
//	public ResponseEntity<?> DeleteTodo(@RequestBody TodoDTO dto) {
//
//		try {
//			String temporaryUserId = "temporary-user"; // temporary user id
//
//			// (1) DTO를 Entity로 변환한다.
//			TodoEntity entity = TodoDTO.toEntity(dto);
//
//			// (2) userId를 temporaryUserId로 설정한다. 인증과 인가는 추후 수정할 예정.
//			entity.setUserId(temporaryUserId);
//
//			// (3) 서비스를 이용해 Entity를 delete한다.
//			List<TodoEntity> entities = service.delete(entity);
//
//			// (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
//			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
//
//			// (5) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
//			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
//
//			// (6) ResponseDTO를 리턴한다.
//			return ResponseEntity.ok().body(response);
//		} catch (Exception e) {
//			// (7) 예외가 있는 경우 dto 대신 error에 메시지를 넣어서 리턴한다.
//			String error = e.getMessage();
//
//			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
//			return ResponseEntity.badRequest().body(response);
//		}
//	}
//
//}










//	@GetMapping("todolist")
//  public ResponseEntity<?> todoControllerResponseEntity()
//  {
//		
//			String str = service.testService();		//테스트 서비스 사용
//			List<String> list = new ArrayList<>();
//			list.add(str);				// 서비스 내용 출력
//			list.add("todo1");
//			list.add("todo2");
////			
//			ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
//			//http status를 200으로 설정
//			return ResponseEntity.ok().body(response);
//			//return ResponseEntity.badRequest().body(response);
//     
//  }
//}
