package com.example.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.example.test.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Configuration
@Slf4j
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and() // WebMvcConfig에서 이미 설정했으므로 기본 cors 설정
            .csrf().disable() // CSRF 보호 비활성화
            .httpBasic().disable() // 기본 HTTP 인증 비활성화
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않음
            .and()
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/auth/**").permitAll() // 인증이 필요 없는 경로
                .anyRequest().authenticated() // 그 외의 경로는 인증 필요
            );

        // JWT 필터를 CORS 필터 이후에 실행하도록 등록
        http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);

        return http.build();
    }
}






//-------------depreciated !!!!!!!!!!!!!!!!!!!!!!!!

//package com.example.test.config;
//
//import org.apache.catalina.filters.CorsFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//import com.example.test.security.JwtAuthenticationFilter;
//
//import lombok.extern.slf4j.Slf4j;
//
//@EnableWebSecurity
//@Slf4j
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Autowired
//	private JwtAuthenticationFilter jwtAuthenticationFilter;
//	
//	@Override
//	protected void configure(HttpSecurity Http) throws Exception
//	//http 시큐리티 빌더
//	http.cors() //WebMvcConfig에서 이미 설정했으므로 기본 cors 설ㅈ어
//	.and()
//	.csrf() //csrf는 현재 사용하지 않으므로 disable
//	.httpBasic() //token을 사용하지 않으므로 basic 인증 disable
//	.disable()
//	.sessionManagement() // session 기반이 아님을 선언
//	.sessionCreationPolicy(SecssionCretionPolicy.STATELESS)
//	.and()
//	.authorizeRequests() // /와 /auth/** 경로는 인증 안해도 됨
//	.antMatchers("/","/auth/**").permitAll()
//	.anyRequest() // /와 /auth/** 이외의 모든 경로는 인증해야 함
//	.authenticated();
//	
//	
//	//filter 등록
//	// 매 요청마다 
//	//CorsFilter 실행한 후에
//	//jwtAuthenticationFilter 실행한다
//	http.addFilterAfter(
//	jwtAuthenticationFilter,
//			CorsFilter.class
//			);
//			
//}
//}