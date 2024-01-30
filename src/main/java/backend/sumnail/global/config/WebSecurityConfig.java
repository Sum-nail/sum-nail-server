package backend.sumnail.global.config;

import backend.sumnail.domain.user.repository.UserRepository;
import backend.sumnail.global.config.jwt.CustomAccessDeniedHandler;
import backend.sumnail.global.config.jwt.CustomAuthenticationEntryPoint;
import backend.sumnail.global.config.jwt.JwtAuthorizationFilter;
import backend.sumnail.global.config.jwt.JwtExceptionFilter;
import backend.sumnail.global.config.jwt.JwtTokenProviderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final UserRepository userRepository;
    private final JwtTokenProviderImpl jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // csrf 보안 토큰 사용 x
                .httpBasic(AbstractHttpConfigurer::disable) // 매 요청마다 id, pwd 보내는 방식 사용 x
                .formLogin(AbstractHttpConfigurer::disable) // formLogin 사용 x
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션 사용 x
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(
                        customAuthenticationEntryPoint)) //커스텀 인증 진입 지점 설정
                .exceptionHandling(exceptionHandling -> exceptionHandling.accessDeniedHandler(
                        customAccessDeniedHandler)) //  커스텀 접근 거부 핸들러 설정
                .addFilter(jwtAuthorizationFilter()) // JWT를 사용하여 인증된 사용자의 권한을 확인하고, 사용자의 인증 정보를 확인
                .addFilterBefore(jwtExceptionFilter(), JwtAuthorizationFilter.class) // JWT 관련 예외 처리
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/v1/auth/**").permitAll()
                        .requestMatchers("/v1/nail-shops/**").permitAll()
                        .requestMatchers("/v1/user/**").permitAll()
                        .requestMatchers("/v1/hashtags").permitAll()
                        .requestMatchers("/v1/stations").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager(); //인증 관리자를 설정
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(authenticationManager(), userRepository, jwtTokenProvider);
    }

    @Bean
    public JwtExceptionFilter jwtExceptionFilter() {
        return new JwtExceptionFilter();
    }
}
