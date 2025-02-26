package park_su_park.backend.dto.requestBody;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import park_su_park.backend.util.validation.Content;

@Getter
@RequiredArgsConstructor
public class CreateCommentRequest {

    @Content
    private final String content;
}
