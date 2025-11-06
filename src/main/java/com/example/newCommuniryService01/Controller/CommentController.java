package com.example.newCommuniryService01.Controller;

import com.example.newCommuniryService01.Dto.CommentDto;
import com.example.newCommuniryService01.Dto.ResponseDto;
import com.example.newCommuniryService01.Service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {


    private CommentService commentService;

    @Autowired
    CommentController(CommentService commentService){
        this.commentService = commentService;
    }





    //댓글 - 추가
    @PostMapping("posts/{postId}/comments")
    public ResponseDto createComment(
            @RequestBody CommentDto commentDto,
            @PathVariable Long postId,
            HttpServletRequest request){


        Long sessionUserId = AuthController.getSessionUserId(request);


        //로그인 여부 필터링
        if(sessionUserId == null){
            return new ResponseDto("로그인이 필요합니다");
        }

        commentService.createComment(commentDto, postId, sessionUserId);


        return new ResponseDto("post_success");
    }




    //댓글 - 수정
    @PatchMapping("posts/{postId}/comments/{commentId}")
    public ResponseDto updatecomment(
            @RequestBody CommentDto commentDto,
            @PathVariable Long postId,
            @PathVariable Long commentId,
            HttpServletRequest request
    ){

        Long sessionUserId = AuthController.getSessionUserId(request);

        //접근 권한 필터링
        if(commentService.updateComment(commentDto, postId, commentId, sessionUserId)){
            return new ResponseDto("접근 권한이 없습니다."); // -> 상태코드로 처리 필요
        }


        return new ResponseDto("update_success");

    }

    //댓글 - 삭제
    @DeleteMapping("posts/{postId}/comments/{commentId}")
    public ResponseDto deletecomment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            HttpServletRequest request
    ){


        Long sessionUserId = AuthController.getSessionUserId(request);

        //접근 권한 필터링
        if(commentService.deleteComment(postId, commentId, sessionUserId)){
            return new ResponseDto("접근 권한이 없습니다."); // -> 상태코드로 처리 필요
        }

        return new ResponseDto("delete_success");


    }



}
