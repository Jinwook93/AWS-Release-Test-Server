package com.example.test.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.test.model.TodoEntity;
import com.example.test.model.UserEntity;

@Repository			// @Component의 특별 케이스로 Service와 마찬가지로 스프링이 관리한다.
public interface UserRepository extends JpaRepository<UserEntity, String>{ 
										// JpaRepository <T,ID>
												//T는 테이블에 매핑될 엔티티 클래스, ID는 엔티티의 기본 키의 타입
	UserEntity findByEmail(String email);
	Boolean existsByEmail(String email);
	UserEntity findByEmailAndPassword(String email,String password);
	
}
