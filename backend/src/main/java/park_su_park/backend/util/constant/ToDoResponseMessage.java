package park_su_park.backend.util.constant;

public interface ToDoResponseMessage {
    String TODO_CREATION_SUCCESS = "일정 생성 성공";
    String TODO_FETCH_SUCCESS = "일정 조회 성공";
    String TODO_UPDATE_SUCCESS = "일정 수정 성공";
    String TODO_DELETION_SUCCESS = "일정 삭제 성공";

    String TODO_FORBIDDEN_ACCESS = "해당 일정을 수정 및 삭제할 권한이 없습니다";
}
