package capstone.project.credit_manager.config.auth;

import capstone.project.credit_manager.web.exception.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            Authentication authentication = jwtTokenProvider.getAuthentication((HttpServletRequest) request);
            if (authentication != null) {
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(authentication);
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            InvalidTokenExceptionHandler(httpResponse);
        }
    }

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
//        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            Authentication auth = jwtTokenProvider.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(auth);
//        }
//        filterChain.doFilter(request, response);
//    }

    private void InvalidTokenExceptionHandler(HttpServletResponse response) throws IOException {
        ErrorResponse error = new ErrorResponse("UNAUTHORIZED", "유효하지 않은 인증토큰 입니다.");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(error));
    }
}
