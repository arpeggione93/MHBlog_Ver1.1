package org.cmh.MHBlog;

import org.cmh.MHBlog.common.dto.SearchDto;
import org.cmh.MHBlog.common.paging.PagingResponse;
import org.cmh.MHBlog.domain.post.PostResponse;
import org.cmh.MHBlog.domain.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RestApiTestController {

    private final PostService postService;

    @GetMapping("/posts")
    public PagingResponse<PostResponse> findAllPost() {
        return postService.findAllPost(new SearchDto());
    }

}
