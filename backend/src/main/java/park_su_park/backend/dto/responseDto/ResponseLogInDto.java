package park_su_park.backend.dto.responseDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ResponseLogInDto {
    public final Long userId;
    public final String username;
}
