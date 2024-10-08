package com.example.test.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity			//자바 클래스를 엔티티로 지정하려면 추가. 만약 엔티티에 이름을 부여하고 싶다면 @Entity("UserEntity")라고 지정
@Table(uniqueConstraints = {@UniqueConstraint(columnNames  = "email")})
public class UserEntity {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id; // 해당 오브젝트의 id
	
	@Column(nullable = false)
	private String username; //사용자의 이름
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password; 
	
	
}
