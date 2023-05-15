package pebite.Ponitor_BE.security;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import pebite.Ponitor_BE.response.Message;
import pebite.Ponitor_BE.response.ResponseMessage;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 JWT를 받아옴

        String token = jwtTokenProvider.resolveToken(request);
        // 유효한 토큰인지 확인
        try {

            if (token != null && jwtTokenProvider.validateToken(token)) {
                // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옴
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                // SecurityContext에 Authentication 객체를 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

            chain.doFilter(request, response);

        }catch (UsernameNotFoundException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ResponseMessage responseMessage = new ResponseMessage(Message.ERR_1030);

            // JSON 응답을 클라이언트에게 전송
            Gson gson = new Gson();
            String jsonString = gson.toJson(responseMessage);
            response.getWriter().write(jsonString);
        }
    }

}
