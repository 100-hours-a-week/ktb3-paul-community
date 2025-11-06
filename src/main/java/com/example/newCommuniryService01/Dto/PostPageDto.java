package com.example.newCommuniryService01.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostPageDto extends DataDto {


    PostDto postDto;

    List<CommentDto> commentDtoList;

    //Boolean liked = false;


    public PostPageDto(
            PostDto postDto,
            List<CommentDto> commentDtoList
    ){
        this.postDto = postDto;
        this.commentDtoList = commentDtoList;
    }

}

