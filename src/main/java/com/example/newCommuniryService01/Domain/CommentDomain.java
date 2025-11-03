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
        sequenceName = "comment_seq",
        allocationSize = 50             //-> allocationSize: DB에서 여러개의 키를 미리 가져와두고, 메모리에서 빠르게 할당
)
public class CommentDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    Long id;
    Long postId;
    Long userId;

    String author;

    String content;

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
