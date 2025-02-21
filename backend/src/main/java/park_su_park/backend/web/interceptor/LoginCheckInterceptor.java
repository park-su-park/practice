package park_su_park.backend.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import park_su_park.backend.exception.UnauthorizedAccessException;
import park_su_park.backend.util.constant.AuthResponseMessage;

public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("userId") == null) {
            throw new UnauthorizedAccessException(AuthResponseMessage.INVALID_LOGIN_CREDENTIAL);
        }

        return true;
    }
}
