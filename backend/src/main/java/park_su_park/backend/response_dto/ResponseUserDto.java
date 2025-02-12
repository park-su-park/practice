package park_su_park.backend.response_dto;

import lombok.Getter;
import lombok.Setter;
import park_su_park.backend.request_dto.RequestUserDto;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseUserDto {
    private Long id;
    private String username;
    private String email;
    private LocalDateTime create_time;


    public static ResponseUserDto createResponseUserDto(Long id, LocalDateTime localDateTime, RequestUserDto requestUserDto) {
        ResponseUserDto responseUserDto = new ResponseUserDto();

        responseUserDto.setId(id);
        responseUserDto.setUsername(requestUserDto.getUsername());
        responseUserDto.setCreate_time(localDateTime);
        responseUserDto.setEmail(requestUserDto.getEmail());

        return responseUserDto;
      }
}
