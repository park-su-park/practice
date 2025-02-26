package park_su_park.backend.exception;

import org.springframework.http.ResponseEntity;
import park_su_park.backend.dto.responseData.UserData;

public class ExpriedSessionException extends RuntimeException {
    public ExpriedSessionException(){
        super("세션이 만료되었습니다.");
    }
}
