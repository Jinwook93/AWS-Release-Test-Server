package com.example.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.model.TodoEntity;
import com.example.test.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoService {

	@Autowired
	private TodoRepository repository;

	public String testService() {
		// TodoEntity 생성
		TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
		// TodoEntity 저장
		repository.save(entity);
		// TodoEntity 검색
		TodoEntity savedEntity = repository.findById(entity.getId()).get();
		return savedEntity.getTitle();

	}

	public List<TodoEntity> retrieve(final String userId) {

		return repository.findByUserId(userId);
	}

	public List<TodoEntity> create(final TodoEntity entity) {

		/*
		 * ==========refactoring //validation if(entity == null) {
		 * log.warn("Entity Cannot Be Null"); throw new
		 * RuntimeException("Entity Cannot be null !!!"); } if(entity.getUserId() ==
		 * null) { log.warn("Unknown User"); throw new
		 * RuntimeException("Unknown User !!!");
		 * 
		 * } ======================
		 */

		validate(entity);

		repository.save(entity);
		log.info("Entity Id : {} is saved.", entity.getId());

		return repository.findByUserId(entity.getUserId());

	}

	private void validate(final TodoEntity entity) {

		if (entity == null) {
			log.warn("Entity Cannot Be Null");
			throw new RuntimeException("Entity Cannot be null !!!");
		}
		if (entity.getUserId() == null) {
			log.warn("Unknown User");
			throw new RuntimeException("Unknown User !!!");

		}

	}

	public List<TodoEntity> update(final TodoEntity entity) {

		// (1) 저장할 엔티티가 유효한지 확인 (validate)
		validate(entity);
		// (2) 넘겨받은 entity id 를 이용해 TodoEntity를 가져온다. 존재하지 않는 엔티티는 업데이트할 수 없기 때문이다.
		final Optional<TodoEntity> original = repository.findById(entity.getId());
		// Optional<TodoEntity>: Optional은 null이 발생할 수 있는 상황을 처리하는 방법으로, 값이 있을 수도 없을 수도
		// 있는 상황을 표현합니다.
		// 이 경우 TodoEntity 객체를 감싸는 Optional 객체를 사용하여, 반환된 엔티티가 존재하지 않을 때의 상황을 처리할 수
		// 있습니다.

		// 방법 1
//		original.ifPresent(todo -> {
//			//(3) 반환된 TodoEntity가 존재하면 값을 새 entity 값으로 덮어 씌운다.
//			todo.setTitle(entity.getTitle());
//			todo.setDone(entity.isDone());
//			
//			//(4) DB에 값을 저장한다
//			repository.save(todo);
//			
//		}); =============

		/// 방법 2 Lambda와 Optional이 익숙하지 않을 경우 다른 방법 사용
		if (original.isPresent()) {
			// (3) 반환된 TodoEntity가 존재하면 값을 새 entity 값으로 덮어 씌운다.
			final TodoEntity todo = original.get();
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());

			// (4) DB에 값을 저장한다
			repository.save(todo);

		}
		
		// =====================

		// Retrieve Todo에서 만든 메서드를 이용해 사용자의 모든 Todo 리스트를 리턴

		return retrieve(entity.getUserId());

	}
	
	public List<TodoEntity> delete (final TodoEntity entity){
		
		
		//1.validation
		validate(entity);
		
		try {
			//2. 엔티티 삭제
			repository.delete(entity);
			
		}catch (Exception e) {
		//3. exception 발생 시 id 와 exception을 로깅
			log.error("error deleting entity",entity.getId());
		}
		
		///5. 새 Todo 리스트를 가져와 리턴
		return retrieve(entity.getUserId());
	}
}
