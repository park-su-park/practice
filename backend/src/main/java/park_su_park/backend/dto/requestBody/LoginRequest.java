package park_su_park.backend.dto.requestBody;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequest {

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String rawPassword;
}
