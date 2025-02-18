package park_su_park.backend.util.constant;

public interface AuthResponseMessage {
    String SIGN_IN_SUCCESS = "회원가입 성공";
    String LOGIN_SUCCESS = "로그인 성공";
    String LOGOUT_SUCCESS = "로그아웃 성공";

    String AUTHENTICATION_FAILED_ACTION = "이메일 혹은 비밀번호가 올바르지 않습니다.";
    String LOGOUT_BAD_REQUEST = "먼저 로그인 해주세요.";
}
