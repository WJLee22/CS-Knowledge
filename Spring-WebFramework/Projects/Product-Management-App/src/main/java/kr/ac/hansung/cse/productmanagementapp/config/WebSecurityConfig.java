package kr.ac.hansung.cse.productmanagementapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/*
WebSecurityConfig: 스프링 시큐리티 설정을 위한 클래스:
@Configuration: 이 클래스가 Spring의 설정 클래스임을 나타냄.
PasswordEncoder: 비밀번호 암호화에 BCrypt 알고리즘을 사용.
AuthenticationManager: 인증 매니저를 Bean으로 등록하여 인증 관련 작업을 처리. 인증 매니저는 사용자 인증을 처리하는 핵심 컴포넌트.
SecurityFilterChain: Spring Security의 핵심 필터 체인을 구성.

- authorizeHttpRequests: (AuthorizationFilter 필터 설정)URL 별 접근 권한을 설정.
    - 정적 리소스, 홈페이지, 회원가입 페이지는 인증 없이 접근 가능
    - 상품 목록은 인증된 사용자만 접근 가능
    - 상품 관리(추가, 수정, 삭제)와 관리자 페이지는 ADMIN 역할을 가진 사용자만 접근 가능

- formLogin: (UsernamePasswordAuthenticationFilter 필터 설정) 로그인 관련 설정.
- logout: (LogoutFilter 필터 설정)로그아웃 관련 설정.
*/

@Configuration
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/about/**",
            "/contact/**",
            "/error/**",
            "/console/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(PUBLIC_MATCHERS).permitAll() // PUBLIC_MATCHERS 에 정의된 정적 리소스들은 인증 없이 접근 가능
                        .requestMatchers("/", "/home", "/signup").permitAll() // 홈 페이지와 회원가입 페이지는 인증 없이 접근 가능
                        .requestMatchers("/products").authenticated() // 상품 목록은 인증된 사용자만 볼 수 있음
                        .requestMatchers("/products/new", "/products/save", "/products/edit/**",
                                        "/products/update", "/products/delete/**").hasRole("ADMIN") // 상품 관리는 ADMIN 권한 필요
                        .requestMatchers("/admin/**").hasRole("ADMIN") // /admin/** 경로는 ADMIN 역할을 가진 사용자만 접근 가능
                        .anyRequest().authenticated() // 나머지 모든 요청들은 인증이 필요
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // 로그인 페이지 URL
                        .defaultSuccessUrl("/home") //로그인 성공 후 이동할 페이지
                        .failureUrl("/login?error") // 로그인 실패 시 이동할 페이지
                        .permitAll() // 로그인 페이지는 인증 없이 접근 가능
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 URL
                        .logoutSuccessUrl("/login?logout") // 로그아웃 성공 후 이동할 페이지
                        .permitAll() // 로그아웃 페이지는 인증 없이 접근 가능
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedPage("/accessDenied")
                )
                .userDetailsService(customUserDetailsService);
               /* .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**"));*/

        return http.build();
    }
}
