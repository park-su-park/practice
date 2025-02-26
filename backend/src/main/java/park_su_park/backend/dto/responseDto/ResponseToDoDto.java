package park_su_park.backend.dto.responseDto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import park_su_park.backend.domain.ToDo;
import park_su_park.backend.dto.ToDoDto;

@Getter
@Setter
@AllArgsConstructor
public class ResponseToDoDto {
    private Long userId;
    private List<ToDoDto> todoes;
}
