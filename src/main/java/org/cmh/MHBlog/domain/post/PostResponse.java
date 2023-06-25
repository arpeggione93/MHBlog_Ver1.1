package org.cmh.MHBlog.domain.post;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class PostResponse {

    private Long id;                       // PK
    private String title;                  // 제목
    private String content;                // 내용
    private String writer;                 // 작성자
    private int viewCnt;                   // 조회 수
    private Boolean noticeYn;              // 공지글 여부
    private Boolean deleteYn;              // 삭제 여부
    private Date registDttm;     // 생성일시
    private Date modifiedDttm;    // 최종 수정일시

}
