package park_su_park.backend.dto.responseData;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import park_su_park.backend.domain.ToDo;

@Getter
@Setter
public class ToDoData implements ResponseData{

    List<ToDoElement> toDoList = new ArrayList<>();

    public static ToDoData of(ToDo toDo) {
        ToDoElement toDoElement = ToDoElement.of(toDo);
        ToDoData toDoData = new ToDoData();
        toDoData.getToDoList().add(toDoElement);
        return toDoData;
    }

    public static ToDoData of(List<ToDo> toDoes) {
        ToDoData toDoData = new ToDoData();
        for (ToDo toDo : toDoes) {
            ToDoElement toDoElement = ToDoElement.of(toDo);
            toDoData.getToDoList().add(toDoElement);
        }
        return toDoData;
    }
}
