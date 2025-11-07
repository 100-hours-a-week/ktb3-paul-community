package com.example.newCommuniryService01.Service;


import com.example.newCommuniryService01.Domain.CommentDomain;
import com.example.newCommuniryService01.Domain.CommentUpdateDomain;
import com.example.newCommuniryService01.Dto.CommentDto;
import com.example.newCommuniryService01.Dto.UserDto;
import com.example.newCommuniryService01.Repository.CommentRepository;
import com.example.newCommuniryService01.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }


    //댓글 - 추가
    public CommentDto createComment(CommentDto commentDto, Long postId, Long sessionUserId){


        //세션 매치해서 가져온 userId 할당
        commentDto.setUserId(sessionUserId);

        CommentDomain commentDomain = new CommentDomain(
                commentDto.getId(),
                postId,
                sessionUserId,
                commentDto.getAuthor(),
                commentDto.getContent()
        );

        commentRepository.save(commentDomain, postId);
        return null;
    }




    //댓글 - 수정
    public Boolean updateComment(CommentDto commentDto, Long postId, Long commentId, Long sessionUserId){

        /* 구 버전
        commentDto.setPostId(postId);
        //
        commentDto.setId(commentId);
        commentDto.setUserId(commentRepository.findById(postId).getUserId());
        commentDto.setAuthor(commentRepository.findById(postId).getAuthor());
        //---
        commentRepository.update(commentDto.toDomain(), commentId);
         */

        CommentDomain commentDomain = commentRepository.findById(commentId);

        //접근 권한 필터링 (본인만)
        if(!sessionUserId.equals(commentDomain.getUserId())){
            return true;
        }


        System.out.println("Content: "+commentDto.getContent());
        CommentUpdateDomain commentUpdateDomain = new CommentUpdateDomain(
                commentDto.getId(),
                commentDto.getPostId(),
                commentDto.getUserId(),
                commentDto.getAuthor(),
                commentDto.getContent()
        );

        commentRepository.update(commentUpdateDomain, commentId);




        return false;
    }



    //댓글 - 삭제
    public Boolean deleteComment(Long postId, Long commentId, Long sessionUserId){


        CommentDomain commentDomain = commentRepository.findById(commentId);

        //접근 권한 필터링
        if(!sessionUserId.equals(commentDomain.getUserId())){
            return true;
        }

        commentRepository.delete(commentId);

        return false;
    }







}
