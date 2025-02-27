package park_su_park.backend.util.constant;

public interface CommentResponseMessage {
    String COMMENT_CREATION_SUCCESS = "댓글 생성 성공";
    String COMMENT_FETCH_SUCCESS = "댓글 조회 성공";
    String COMMENT_FETCH_FAILED = "댓글 조회 실패";
    String COMMENT_UPDATE_SUCCESS = "댓글 수정 성공";
    String COMMENT_DELETION_SUCCESS = "댓글 삭제 성공";

    String COMMENT_FORBIDDEN_ACTION = "해당 댓글을 수정 및 삭제할 권한이 없습니다";
}
