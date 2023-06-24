package org.cmh.MHBlog.domain.comment;

import org.cmh.MHBlog.common.dto.SearchDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentSearchDto extends SearchDto {

    private Long postId;    // 게시글 번호 (FK)

}