package park_su_park.backend.dto.responseDto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUserDto {
    private Long Id;
    private String username;
    private String email;
    private LocalDateTime createTime;
}
