package park_su_park.backend.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import park_su_park.backend.domain.ToDo;
import park_su_park.backend.domain.User;
import park_su_park.backend.dto.ToDoDto;
import park_su_park.backend.dto.requestDto.RequestToDoDto;
import park_su_park.backend.dto.responseDto.ResponseDto;
import park_su_park.backend.dto.responseDto.ResponseToDoDto;
import park_su_park.backend.exception.NotExistToDoException;
import park_su_park.backend.exception.NotExistUserException;
import park_su_park.backend.repository.ToDoRepository;
import park_su_park.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ToDoService {
    private final ToDoRepository toDoRepository;
    private final UserRepository userRepository;

    public ResponseDto save(Long userId, RequestToDoDto requestToDoDto) {
        User findUser = userRepository.findById(userId)
            .orElseThrow(() -> new NotExistUserException("유저가 존재하지 않습니다."));
        //Dto -> ToDo 생성
        ToDo toDo = ToDo.create(findUser,requestToDoDto);
        toDoRepository.save(toDo);
        ResponseToDoDto responseToDoDto = getResponseToDoDto(toDo,
            findUser);
        return new ResponseDto("ToDo 생성 성공", responseToDoDto);
    }

    //한개의 toDo를 reponseToDoDto 형식으로 변환
    private static ResponseToDoDto getResponseToDoDto(ToDo toDo, User user) {
        ToDoDto toDoDto = ToDoDto.of(toDo);
        List<ToDoDto> toDoDtoes = Arrays.asList(toDoDto);
        ResponseToDoDto responseToDoDto = new ResponseToDoDto(user.getId(), toDoDtoes);
        return responseToDoDto;
    }

    public ResponseDto findByUser(Long userId) {
        User findUser = userRepository.findById(userId)
            .orElseThrow(() -> new NotExistUserException("유저가 존재하지 않습니다."));
        List<ToDo> toDoes = findUser.getToDoes();
        List<ToDoDto> toDoDtoes = new ArrayList<>();
        for (ToDo toDo : toDoes) {
            ToDoDto toDoDto = ToDoDto.of(toDo);
            toDoDtoes.add(toDoDto);
        }
        ResponseToDoDto responseToDoDto = new ResponseToDoDto(findUser.getId(), toDoDtoes);
        return new ResponseDto("할일 목록 조회 성공", responseToDoDto);
    }

    @Transactional
    public ResponseToDoDto update(Long userId, Long toDoId, RequestToDoDto requestToDoDto) {
        User findUser = userRepository.findById(userId)
            .orElseThrow(() -> new NotExistUserException("유저가 존재하지 않습니다."));
        List<ToDo> toDoes = findUser.getToDoes();
        ResponseToDoDto responseToDoDto;
        for (ToDo toDo : toDoes) {
            if (toDo.getId().equals(toDoId)) {
                toDo.setTitle(requestToDoDto.getTitle());
                toDo.setContent(requestToDoDto.getContent());
                return getResponseToDoDto(toDo, findUser);
            }
        }
        throw new NotExistToDoException("ToDo가 존재하지 않습니다.");
    }

    @Transactional
    public void delete(Long userId, Long toDoId) {
        User findUser = userRepository.findById(userId)
            .orElseThrow(() -> new NotExistUserException("유저가 존재하지 않습니다."));
        findUser.getToDoes().removeIf(toDo -> {
            return toDo.getId().equals(toDoId);
        });
        toDoRepository.deleteById(toDoId);
    }
}
