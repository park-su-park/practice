package park_su_park.backend.dto.requestBody;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateToDoRequest {

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;
}
