package com.example.newCommuniryService01.Dto;

import com.example.newCommuniryService01.Domain.UserMode;
import lombok.Getter;

@Getter
public class PostFetchDto {

    private Long id = 0L;
    private Long userId = 0L;
    private String author = "";

    private String title = "";
    private String content = "";

    private Boolean adminOnly = false;

    private UserMode userMode = null;



    public PostFetchDto(
            Long id,
            Long userId,
            String author,
            String title,
            String content,
            Boolean adminOnly,
            UserMode userMode

    ){

        this.id = id;
        this.userId = userId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.adminOnly = adminOnly;
        this.userMode = userMode;

    }



}
