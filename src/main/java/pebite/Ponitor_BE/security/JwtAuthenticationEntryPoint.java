package pebite.Ponitor_BE.security;

import com.google.gson.Gson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import pebite.Ponitor_BE.response.Message;
import pebite.Ponitor_BE.response.ResponseMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component  // 인증이 되지 않은 유저가 요청했을 때 동작
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ResponseMessage responseMessage = new ResponseMessage(Message.ERR_1020);

        // JSON 응답을 클라이언트에게 전송
        Gson gson = new Gson();
        String jsonString = gson.toJson(responseMessage);
        response.getWriter().write(jsonString);
    }

}
