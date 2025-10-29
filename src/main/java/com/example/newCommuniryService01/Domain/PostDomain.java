package com.example.newCommuniryService01.Domain;


import com.example.newCommuniryService01.Dto.PostDto;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.swing.text.StyledEditorKit;

@Getter
@Setter
@Entity
@SequenceGenerator(
        name = "post_seq",
        sequenceName = "post_seq",
        allocationSize = 50             //-> allocationSize: DB에서 여러개의 키를 미리 가져와두고, 메모리에서 빠르게 할당
)
public class PostDomain {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq")
    private Long id = 0L;

    private Long userId = 0L;
    //@Column(length = 200)
    private String author = "";

    private String title = "";
    private String content = "";

    //부여할 조회권한별 분기 필요 (관리자에게만, 로그인에게만, 모두에게, +나에게만)
    // (-> 일단 지금은 adminOnly으로만 분기, 열거형 필드로 수정 후 나중에 '로그인,모두,나에게' 등 추가하기)
    private Boolean adminOnly = false;




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
            Boolean adminOnly
    ){

        this.id = id;
        this.userId = userId;
        this.author = author;
        this.title = title;
        this.content = content;
        this.adminOnly = adminOnly;

    }


    public PostDto toDto(){

        PostDto postDto
                = new PostDto(
                id,
                userId,
                author,
                title,
                content,
                adminOnly
        );
        return postDto;
    }


}
