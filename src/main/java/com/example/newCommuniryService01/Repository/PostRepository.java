package com.example.newCommuniryService01.Repository;

import com.example.newCommuniryService01.Domain.PostDomain;
import com.example.newCommuniryService01.Dto.CommentDto;
import com.example.newCommuniryService01.Dto.PostDto;
import com.example.newCommuniryService01.Dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface PostRepository  {


    //게시글 추가 & 전체조회
    public PostDomain save(PostDomain postDomain);
    public Map<Long, PostDomain> findAll(String page, Long size);

    // 게시글 상세조회
    public PostDomain memoryFindById(Long id); //findById는 Jpa에 이미 존재 -> 충돌


    //postId로 userId가져오기
    public Long getUserId(Long postId);

    //게시글 수정, 삭제
    public PostDomain update(PostDomain postDomain, Long postId);
    public PostDomain delete(Long postId);





    /*

    게시글 추가 () / userId
    게시글 전체조회 ()

    게시글 상세조회 (postId)
        ㄴ댓글 조회 (postId)

    게시글 수정, 삭제 (postId) / userId
    댓글 추가, 수정, 삭제 (commentId) / userId


     */


}
