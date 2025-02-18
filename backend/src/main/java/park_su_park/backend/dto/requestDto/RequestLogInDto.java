package park_su_park.backend.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestLogInDto {
    @Email
    private String email;
    @NotNull(message = "비밀번호를 반드시 입력해야합니다.")
    private String password;
}
