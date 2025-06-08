package kr.ac.hansung.cse.hellospringbootsecurity.config;

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
                        .requestMatchers(PUBLIC_MATCHERS).permitAll() // PUBLIC_MATCHERS 에 정의된 URL은 인증 없이 접근 가능
                        .requestMatchers("/", "/home", "/signup").permitAll() // 홈 페이지와 회원가입 페이지는 인증 없이 접근 가능
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
