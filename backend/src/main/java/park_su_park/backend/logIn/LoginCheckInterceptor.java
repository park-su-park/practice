package park_su_park.backend.logIn;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import park_su_park.backend.exception.LogInException;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터세버 실행 {}", requestURI);
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(LogInterface.LOGIN_USER) == null) {
            log.info("미인증 사용자 요청");
            throw new LogInException("미인증 사용자 요청");
        }
        return true;
    }
}
