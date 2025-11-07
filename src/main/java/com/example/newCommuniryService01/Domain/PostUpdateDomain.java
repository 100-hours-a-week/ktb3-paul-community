package com.example.newCommuniryService01.Domain;

import com.example.newCommuniryService01.Dto.PostFetchDto;
import lombok.Getter;

@Getter
public class PostUpdateDomain {

    public PostUpdateDomain(
            Long id,
            Long userId,
            String author,
            String title,
            String content,
            Boolean adminOnly,
            PostAuthority postAuthority
    ){

        this.id = id;
        this.userId = userId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.adminOnly = adminOnly;
        this.postAuthority = postAuthority;

    }

    private Long id = 0L;

    private Long userId = 0L;
    private String author = "";

    private String title = "";
    private String content = "";

    private Boolean adminOnly = false;
    private PostAuthority postAuthority = null;








}
