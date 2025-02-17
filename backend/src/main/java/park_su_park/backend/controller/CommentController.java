package park_su_park.backend.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import park_su_park.backend.dto.requestBody.CreateCommentRequest;
import park_su_park.backend.dto.responseBody.CommentDataResponse;
import park_su_park.backend.service.CommentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/create/{toDoId}")
    public ResponseEntity<CommentDataResponse> createComment(@RequestBody @Valid CreateCommentRequest createCommentRequest, @PathVariable Long toDoId, HttpSession session) {
        CommentDataResponse commentDataResponse = commentService.createComment(createCommentRequest, toDoId, session);

        return ResponseEntity.status(HttpStatus.CREATED).body(commentDataResponse);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDataResponse> getComment(@PathVariable Long commentId) {
        CommentDataResponse commentDataResponse = commentService.getComment(commentId);

        return ResponseEntity.ok(commentDataResponse);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDataResponse> updateComment(@RequestBody @Valid CreateCommentRequest updateCommentRequest, @PathVariable Long commentId, HttpSession session) {
        CommentDataResponse commentDataResponse = commentService.updateComment(updateCommentRequest, commentId, session);

        return ResponseEntity.ok(commentDataResponse);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, HttpSession session) {
        commentService.deleteComment(commentId, session);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
