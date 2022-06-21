package com.abhi.bloggerbook.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

    private List<PostDto> postDtos;

    private int pageNumber;

    private int pageSize;

    private  int totalPages;

    private long totalElements;

    private boolean isLastPage;

}
