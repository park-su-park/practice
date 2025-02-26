package park_su_park.backend.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import park_su_park.backend.domain.ToDo;
import park_su_park.backend.dto.responseDto.ResponseToDoDto;

@Getter
@Setter
public class ToDoDto {
    private Long toDoId;
    private String title;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public static ToDoDto of(ToDo toDo) {
        ToDoDto toDoDto = new ToDoDto();
        toDoDto.setToDoId(toDo.getId());
        toDoDto.setTitle(toDo.getTitle());
        toDoDto.setContent(toDo.getContent());
        toDoDto.setCreatedTime(toDo.getCreatedTime());
        toDoDto.setUpdatedTime(toDo.getUpdatedTime());
        return toDoDto;
    }
}
