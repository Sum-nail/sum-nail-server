package backend.sumnail.global.config.jwt;

import backend.sumnail.domain.user.entity.User;
import backend.sumnail.domain.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;
    private final JwtTokenProviderImpl jwtTokenProvider;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository,
                                  JwtTokenProviderImpl jwtTokenProvider) {

        super(authenticationManager);
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader(JwtProperties.HEADER_STRING);

        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, ""); // Bearer 제거

        Long userId = jwtTokenProvider.getUserIdFromAccessToken(token); // 토큰 검증 -> 인증 완료 (AuthenticationManager 대체)

        if (userId != null) { // 권한 구분 위해 Authentication 객체를 생성해 세션에 저장

            User user = userRepository.getById(userId);

            // 인증은 토큰 검증시 끝. 인증을 하기 위해서가 아닌 스프링 시큐리티가 수행해주는 권한 처리를 위해
            // 아래와 같이 토큰을 만들어서 Authentication 객체를 강제로 만들고 그걸 세션에 저장!
            PrincipalDetails principalDetails = new PrincipalDetails(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null,
                    principalDetails.getAuthorities());
            // 강제로 시큐리티의 세션에 접근하여 값 저장. 이때 자동으로 UserDetailsService의 loadByUsername 호출됨
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response); // 다음 필터로 진행
    }
}
