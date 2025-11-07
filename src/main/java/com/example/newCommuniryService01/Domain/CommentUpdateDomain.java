package com.example.newCommuniryService01.Domain;

public class CommentUpdateDomain extends CommentDomain{


    public CommentUpdateDomain(
            Long id,
            Long postId,
            Long userId,
            String author,
            String content
    ){

        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.author = author;
        this.content= content;

    }


}
