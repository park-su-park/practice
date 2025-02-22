package park_su_park.backend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.ToDo;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.ToDoDto;
import park_su_park.backend.dto.requestDto.RequestToDoDto;
import park_su_park.backend.dto.responseData.ToDoData;
import park_su_park.backend.exception.NotExistException;
import park_su_park.backend.message.TODOMESSAGE;
import park_su_park.backend.message.USERMESSAGE;
import park_su_park.backend.repository.ToDoRepository;
import park_su_park.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ToDoService {

    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;

    public ToDoData save(Long userId, RequestToDoDto requestToDoDto) {
        User foundUser = userRepository.findById(userId)
            .orElseThrow(() -> new NotExistException(USERMESSAGE.NOT_EXIST));
        //Dto -> ToDo 생성
        ToDo toDo = ToDo.create(foundUser, requestToDoDto);
        toDoRepository.save(toDo);
        return ToDoData.of(toDo);
    }

    public ToDoData findAll(Long userId) {
        User foundUser = userRepository.findById(userId)
            .orElseThrow(() -> new NotExistException(USERMESSAGE.NOT_EXIST));
        List<ToDo> toDoes = foundUser.getToDoes();
        return ToDoData.of(toDoes);
    }

    public ToDoData findOne(Long userId, Long toDoId) {
        User foundUser = userRepository.findById(userId)
            .orElseThrow(() -> new NotExistException(USERMESSAGE.NOT_EXIST));
        ToDo foundToDo = toDoRepository.findByUserIdAndId(userId, toDoId).
            orElseThrow(() -> new NotExistException(TODOMESSAGE.NOT_EXIST));
        return ToDoData.of(foundToDo);
    }

    @Transactional
    public ToDoData update(Long userId, Long toDoId, RequestToDoDto requestToDoDto) {
        User foundUser = userRepository.findById(userId)
            .orElseThrow(() -> new NotExistException(USERMESSAGE.NOT_EXIST));
        ToDo foundToDo = toDoRepository.findByUserIdAndId(userId, toDoId).
            orElseThrow(() -> new NotExistException(TODOMESSAGE.NOT_EXIST));
        updateToDo(requestToDoDto, foundToDo);
        return ToDoData.of(foundToDo);
    }

    private void updateToDo(RequestToDoDto requestToDoDto, ToDo toDo) {
        if (requestToDoDto.getTitle() != null) {
            toDo.setTitle(requestToDoDto.getTitle());
        }
        if (requestToDoDto.getContent() != null) {
            toDo.setContent(requestToDoDto.getContent());
        }
    }

    @Transactional
    public void delete(Long userId, Long toDoId) {
        User foundUser = userRepository.findById(userId)
            .orElseThrow(() -> new NotExistException(USERMESSAGE.NOT_EXIST));
        ToDo foundToDo = toDoRepository.findByUserIdAndId(userId, toDoId).
            orElseThrow(() -> new NotExistException(TODOMESSAGE.NOT_EXIST));
        toDoRepository.delete(foundToDo);
    }
}
