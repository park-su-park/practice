package park_su_park.backend.logIn;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "my_sessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    //세션 생성
    public void createSession(Object value, HttpServletResponse response) {
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        response.addCookie(cookie);
    }

    //세션 조회 기능
    public Object getSession(HttpServletRequest request) {
        Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
        if (cookie == null) {
            return null;
        }
        return sessionStore.get(cookie.getValue());
    }

    private Cookie findCookie(HttpServletRequest request, String cookieName) {
        if(request.getCookies() == null){
            return null;
        }

        return Arrays.stream(request.getCookies())
            .filter(c -> c.getName().equals(cookieName))
            .findAny()
            .orElse(null);
    }

    public void expire(HttpServletRequest request) {
        Cookie cookie = findCookie(request, SESSION_COOKIE_NAME);
        if(cookie != null){
            sessionStore.remove(cookie.getValue());
        }
    }
}
