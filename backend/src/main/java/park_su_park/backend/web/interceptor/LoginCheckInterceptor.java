package park_su_park.backend.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import park_su_park.backend.exception.UnauthorizedAccessException;

public class LoginCheckInterceptor implements HandlerInterceptor {

    public static final String INVALID_LOGIN_CREDENTIAL = "로그인 정보가 유효하지 않거나 만료 되었습니다.";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("userId") == null) {
            throw new UnauthorizedAccessException(INVALID_LOGIN_CREDENTIAL);
        }

        return true;
    }
}
