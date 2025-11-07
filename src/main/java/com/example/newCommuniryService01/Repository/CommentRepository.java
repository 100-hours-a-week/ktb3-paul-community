package com.example.newCommuniryService01.Repository;

import com.example.newCommuniryService01.Domain.CommentDomain;
import com.example.newCommuniryService01.Domain.PostDomain;
import com.example.newCommuniryService01.Dto.CommentDto;
import com.example.newCommuniryService01.Dto.PostDto;

import java.util.List;
import java.util.Map;

public interface CommentRepository {



    public CommentDomain save(CommentDomain commentDomain, Long postId);

    public List<CommentDomain> findAll(Long postId);

    public CommentDomain findById(Long id);

    public CommentDomain update(CommentDomain commentDomain, Long commentId);

    public CommentDomain delete(Long commentId);





    public List<CommentDto> listingComment(Long postId);
    public Long getUserId(Long postId);


    /*

    게시글 추가 () / userId
    게시글 전체조회 ()

    게시글 상세조회 (postId)
        ㄴ댓글 조회 (postId)

    게시글 수정, 삭제 (postId) / userId
    댓글 추가, 수정, 삭제 (commentId) / userId


     */


}
