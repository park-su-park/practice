package park_su_park.backend.dto.requestBody;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCommentRequest {

    @NotBlank
    private final String content;
}
