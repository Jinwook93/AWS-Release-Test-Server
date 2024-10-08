package com.example.test.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.test.model.TodoEntity;

@Repository			// @Component의 특별 케이스로 Service와 마찬가지로 스프링이 관리한다.
public interface TodoRepository extends JpaRepository<TodoEntity, String>{ 
										// JpaRepository <T,ID>
												//T는 테이블에 매핑될 엔티티 클래스, ID는 엔티티의 기본 키의 타입
	// ?1은 매서드의 매게변수의 순서 위치
	//@Query("select * from Todo t where t.userId =?1")
	List<TodoEntity> findByUserId(String userId);

	//JpaRepository를 상속받는 인터페이스
}
