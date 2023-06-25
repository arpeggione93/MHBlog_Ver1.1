package org.cmh.MHBlog.domain.comment;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class CommentResponse {

    private Long id;                       // 댓글 번호 (PK)
    private Long postId;                   // 게시글 번호 (FK)
    private String content;                // 내용
    private String writer;                 // 작성자
    private Boolean deleteYn;              // 삭제 여부
    private Date registDttm;     // 생성일시
    private Date modifiedDttm;    // 최종 수정일시

}
