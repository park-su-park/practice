package park_su_park.backend.dto.requestBody;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateUserRequest {

    @NotBlank
    private final String username;

    @NotBlank
    private final String rawPassword;

    @Email
    @NotBlank
    private final String email;
}
