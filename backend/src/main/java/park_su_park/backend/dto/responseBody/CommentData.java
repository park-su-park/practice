package park_su_park.backend.dto.responseBody;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import park_su_park.backend.domain.Comment;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentData implements ApiResponseData {

    private final Long commentId;
    private final Long userId;
    private final Long toDoId;
    private final String content;
    private final LocalDateTime createdTime;
    private final LocalDateTime updateTime;

    //==생성 메서드==//
    public static CommentData create(Comment comment) {
        return new CommentData(comment.getId(), comment.getUser().getId(), comment.getToDo().getId(), comment.getContent(), comment.getCreatedTime(), comment.getUpdateTime());
    }
}
