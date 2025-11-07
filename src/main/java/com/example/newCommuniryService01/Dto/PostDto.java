package com.example.newCommuniryService01.Dto;

import com.example.newCommuniryService01.Domain.PostAuthority;
import com.example.newCommuniryService01.Domain.PostDomain;
import com.example.newCommuniryService01.Domain.UserMode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostDto {



    private Long id = 0L;
    private Long userId = 0L;
    private String author = "";

    private String title = "";
    private String content = "";

    //부여할 조회권한별 분기 필요 (관리자에게만, 로그인에게만, 모두에게, +나에게만)
    // (-> 일단 지금은 adminOnly으로만 분기, 열거형 필드로 수정 후 나중에 '로그인,모두,나에게' 등 추가하기)
    private Boolean adminOnly = false;

    private PostAuthority postAuthority = null;



    public PostDto(
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






    public PostDomain toDomain(){


        PostDomain postDomain
                = new PostDomain(
                id,
                userId,
                author,
                title,
                content,
                adminOnly,
                postAuthority

        );
        return postDomain;
    }




    //유효성 검사
    public Boolean validation(){

        if(this.title == null){
            return true;
        }

        return false;
    }






}
