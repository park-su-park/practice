package park_su_park.backend.dto.responseDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class ErrorResponseDto {

    private final String message;

}
