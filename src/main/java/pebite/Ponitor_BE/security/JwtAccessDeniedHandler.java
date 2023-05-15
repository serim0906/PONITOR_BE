package pebite.Ponitor_BE.security;


import com.google.gson.Gson;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import pebite.Ponitor_BE.response.Message;
import pebite.Ponitor_BE.response.ResponseMessage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component // 서버에 요청을 할 때 액세스가 가능한지 권한을 체크한 후 액세스 할 수 없는 요청 했을 때 동작
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //필요한 권한이 없이 접근하려 할때 403
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ResponseMessage responseMessage = new ResponseMessage(Message.ERR_1000);

        // JSON 응답을 클라이언트에게 전송
        Gson gson = new Gson();
        String jsonString = gson.toJson(responseMessage);
        response.getWriter().write(jsonString);
    }

}

