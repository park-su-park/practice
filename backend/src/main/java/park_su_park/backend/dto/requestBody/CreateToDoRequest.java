package park_su_park.backend.dto.requestBody;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import park_su_park.backend.util.validation.Content;
import park_su_park.backend.util.validation.Title;

@Getter
@RequiredArgsConstructor
public class CreateToDoRequest {

    @Title
    private final String title;

    @Content
    private final String content;
}
