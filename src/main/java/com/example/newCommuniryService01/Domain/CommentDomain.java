package com.example.newCommuniryService01.Domain;

import com.example.newCommuniryService01.Dto.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@SequenceGenerator(
        name = "comment_seq",
        sequenceName = "comment_seq"
        //allocationSize = 50             //-> allocationSize: DB에서 여러개의 키를 미리 가져와두고, 메모리에서 빠르게 할당
)
public class CommentDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    Long id;
    Long postId;
    Long userId;

    String author;

    String content;






    //default 생성자 필요 (Jpa, 하이버네이트)
    protected CommentDomain(){}





    public CommentDomain(
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






    public CommentUpdateDomain updateEntity(CommentUpdateDomain cud){

        Long cudId = cud.getId();
        Long cudPostId = cud.getPostId();
        Long cudUserId = cud.getUserId();
        String cudAuthor = cud.getAuthor();
        String cudContent = cud.getContent();


        if(cudId!= null) this.id = cudId;
        if(cudUserId != null) this.userId = cudUserId;
        if(cudPostId != null) this.postId = cudPostId;
        if(cudAuthor != null) this.author = cudAuthor;
        if(cudContent != null) this.content = cudContent;



        return null;
    }









    public CommentDto toDto(){
        CommentDto commentDto
                = new CommentDto(
                id,
                postId,
                userId,
                author,
                content
        );
        return commentDto;
    }



}
