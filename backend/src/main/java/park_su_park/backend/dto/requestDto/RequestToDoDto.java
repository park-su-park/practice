package park_su_park.backend.dto.requestDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestToDoDto {
    @NotEmpty(message = "제목을 압력하세요.")
    public String title;
    @NotNull(message = "내용을 입력하세요.")
    public String content;
}
