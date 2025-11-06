package com.example.newCommuniryService01.Domain;


import com.example.newCommuniryService01.Dto.PostDto;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@SequenceGenerator(
        name = "post_seq",
        sequenceName = "post_seq"
        //allocationSize = 50             //-> allocationSize: DB에서 여러개의 키를 미리 가져와두고, 메모리에서 빠르게 할당
)
public class PostDomain {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    private Long id = 0L;

    private Long userId = 0L;
    private String author = "";

    //@Column(length = 200)
    private String title = "";
    private String content = "";

    //부여할 조회권한별 분기 필요 (관리자에게만, 로그인에게만, 모두에게, +나에게만)
    // (-> 일단 지금은 adminOnly으로만 분기, 열거형 필드로 수정 후 나중에 '로그인,모두,나에게' 등 추가하기)
    private Boolean adminOnly = false;
    private PostAuthority postAuthority = null;



    //default 생성자 필요 (Jpa, 하이버네이트)
    protected PostDomain(){}



    //피드백: 도메인 데이터 불변성 문제
    /*
    - 요구 작업: 추가, 수정 / 조회

     - 추가: @RequestBody, dto생성 > toDomain
     - 수정(관건):
     - 조회: Domain겟 > toDto
     */
    public PostDomain(
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




    public PostUpdateDomain updateEntity(PostUpdateDomain pud){

        Long pudId = pud.getId();
        Long pudUserId = pud.getUserId();
        String pudAuthor = pud.getAuthor();
        String pudTitle = pud.getTitle();
        String pudContent = pud.getContent();
        Boolean pudAdminOnly = pud.getAdminOnly();
        PostAuthority pudPostAuthority = pud.getPostAuthority();


        if(pudId!= null) this.id = pudId;
        if(pudUserId != null) this.userId = pudUserId;
        if(pudAuthor != null) this.author = pudAuthor;
        if(pudTitle != null) this.title = pudTitle;
        if(pudContent != null) this.content = pudContent;
        if(pudAdminOnly != null) this.adminOnly = pudAdminOnly;



        return null;
    }




    public PostDto toDto(){

        PostDto postDto
                = new PostDto(
                id,
                userId,
                author,
                title,
                content,
                adminOnly,
                postAuthority
        );
        return postDto;
    }




    /*
    public PostDomain checkNull(PostDomain postDomain){

        Map<Object, Long> thisMap = new HashMap<>();
        thisMap.put(this.id, 1L);
        thisMap.put(this.userId, 2L);
        thisMap.put(this.author, 3L);
        thisMap.put(this.title, 4L);
        thisMap.put(this.content, 5L);
        thisMap.put(this.adminOnly, 6L);


        for(Object object : thisMap.values()){
            if(object != null){

            }


        }





        return null;
    }

     */











/*
    public void refresh(PostDomain postDomain){
        this.userId = postDomain.userId;
        this.author = postDomain.author;
        this.title = postDomain.title;
        this.content = postDomain.content;
        this.adminOnly = postDomain.adminOnly;
    }

 */



}
