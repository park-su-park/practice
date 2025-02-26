package park_su_park.backend.util.constant;

public interface ValidationMessage {
    String REQUIRED_FIELD_MESSAGE = "공백의 내용은 허용하지 않습니다.";

    String NO_WHITESPACE_ALLOWED = "공백이 포함된 입력은 허용하지 않습니다.";

    String DEFAULT_EMAIL_VALIDATION_FAILED_MESSAGE = "이메일 형식 검증 실패";
    String EMAIL_FORMAT_MESSAGE = "이메일 형식이 유효하지 않습니다.";

    String MAX_TITLE_SIZE_MESSAGE = "일정의 제목은 공백을 허용하지 않으며 30자 이내로 작성해주세요.";

    String USERNAME_SIZE_MESSAGE = "사용자명은 5자에서 15자 이내로 작성해주세요.";

    String DEFAULT_PASSWORD_VALIDATION_FAILED_MESSAGE = "비밀번호 형식 검증 실패";
    String PASSWORD_SIZE_MESSAGE = "비밀번호는 8자에서 20자 이내로 작성해주세요.";
    String PASSWORD_PATTER_MESSAGE = "비밀번호는 영문 대소문자와 숫자 그리고 특수문자를 반드시 하나 이상 조합하여 작성해주세요.";

    String DEFAULT_USERNAME_VALIDATION_FAILED_MESSAGE = "사용자명 형식 검증 실패";
}
