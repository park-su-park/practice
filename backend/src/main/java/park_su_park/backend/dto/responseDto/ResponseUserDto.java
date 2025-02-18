package park_su_park.backend.dto.responseDto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import park_su_park.backend.domain.User;

@Getter
@Setter
public class ResponseUserDto {

    private Long id;
    private String username;
    private String email;
    private LocalDateTime createTime;


    public static ResponseUserDto of(User savedUser) {
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setId(savedUser.getId());
        responseUserDto.setUsername(savedUser.getUsername());
        responseUserDto.setEmail(savedUser.getEmail());
        responseUserDto.setCreateTime(savedUser.getCreateTime());
        return responseUserDto;
    }
}