package com.example.newCommuniryService01.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostListDto extends DataDto{

    List<PostFetchDto> pfdList;

    public PostListDto(List<PostFetchDto> pfdList){
        this.pfdList = pfdList;
    }

}

