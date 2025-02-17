package park_su_park.backend.dto.responseBody;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import park_su_park.backend.domain.ToDo;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ToDoDataResponse {

    private final Long toDoId;
    private final Long userId;
    private final String title;
    private final String content;
    private final LocalDateTime createdTime;
    private final LocalDateTime updateTime;

    //==생성 메서드==//
    public static ToDoDataResponse create(ToDo toDo){
        return new ToDoDataResponse(toDo.getId(), toDo.getUser().getId(), toDo.getTitle(), toDo.getContent(), toDo.getCreatedTime(), toDo.getUpdateTime());
    }
}
