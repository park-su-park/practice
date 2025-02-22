package park_su_park.backend.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUserDto {


    @Size(max = 8, message = "잘못된 사용자명 형식입니다.",groups = {CreateUser.class,UpdateUser.class})
    private String username;
    @NotNull(message = "비밀번호를 반드시 입력해야합니다.",groups = CreateUser.class)
    private String password;
    @Email(message = "이메일 형식이 아닙니다",groups = CreateUser.class)
    private String email;
}
