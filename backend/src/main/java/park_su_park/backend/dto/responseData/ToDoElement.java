package park_su_park.backend.dto.responseData;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import park_su_park.backend.domain.ToDo;

@Getter
@Setter
public class ToDoElement {
    private Long toDoId;
    private Long userid;
    private String title;
    private String content;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public static ToDoElement of(ToDo toDo) {
        ToDoElement toDoElement = new ToDoElement();
        toDoElement.setToDoId(toDo.getId());
        toDoElement.setUserid(toDo.getUser().getId());
        toDoElement.setTitle(toDo.getTitle());
        toDoElement.setContent(toDo.getContent());
        toDoElement.setCreatedTime(toDo.getCreatedTime());
        toDoElement.setUpdatedTime(toDo.getUpdatedTime());
        return toDoElement;
    }
}
