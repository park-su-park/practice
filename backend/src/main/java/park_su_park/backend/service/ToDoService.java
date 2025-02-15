package park_su_park.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.ToDo;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.requestBody.CreateToDoRequest;
import park_su_park.backend.exception.ResourceNotFoundException;
import park_su_park.backend.repository.ToDoRepository;

import java.text.MessageFormat;

@Service
@Transactional
@RequiredArgsConstructor
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final UserService userService;

    public Long join(CreateToDoRequest createToDoRequest, Long userId) {
        ToDo toDo = ToDo.create(createToDoRequest);

        User user = userService.findUserById(userId);
        toDo.setUser(user);
        ToDo savedToDo = toDoRepository.save(toDo);

        return savedToDo.getId();
    }

    @Transactional(readOnly = true)
    public ToDo findToDo(Long toDoId) {
        return toDoRepository.findById(toDoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        MessageFormat.format("해당 id 를 가진 일정을 찾을 수 없습니다: {0}", toDoId)
                ));
    }

    public void updateToDo(CreateToDoRequest updateToDoRequest, ToDo oldToDo){
        oldToDo.update(updateToDoRequest);
    }

    public void deleteToDo(ToDo toDo) {
        toDoRepository.delete(toDo);
    }
}
