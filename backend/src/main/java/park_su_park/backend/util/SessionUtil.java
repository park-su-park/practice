package park_su_park.backend.util;

import jakarta.servlet.http.HttpSession;
import park_su_park.backend.exception.UnauthorizedAccessException;

public class SessionUtil {

    private static final String USER_ID_SESSION_KEY = "userId";
    private final static String UNAUTHORIZED_ACCESS_ACTION = "로그인 정보가 유효하지 않거나 만료 되었습니다.";

    public static Long getUserIdFromSession(HttpSession session) {
        Long userId = (Long) session.getAttribute(USER_ID_SESSION_KEY);
        if (userId == null) {
            throw new UnauthorizedAccessException(UNAUTHORIZED_ACCESS_ACTION);
        }
        return userId;
    }
}
