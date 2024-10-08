package com.example.test.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity			//자바 클래스를 엔티티로 지정하려면 추가. 만약 엔티티에 이름을 부여하고 싶다면 @Entity("todoEntity")라고 지정
@Table(name="Todo")	// DB의 Todo 테이블에 매핑된다는 뜻. 테이블 이름 지정을 하려면 name을 사용.
					//만약  Table을 추가하지 않거나 추가를 해도 name을 명시하지 않았을 경우 @Entity의 이름을 테이블 이름으로 간주한다.
public class TodoEntity {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id; // 해당 오브젝트의 id
	private String userId; // 이 오브젝트를 생성한 사용자의 아이디
	private String title; // Todo 타이틀 (ex : 운동하기)
	private boolean done; //true - todo를 완료한 경우
	
	
}
