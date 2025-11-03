package com.example.newCommuniryService01.Controller;

import com.example.newCommuniryService01.Dto.*;
import com.example.newCommuniryService01.Service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.List;

@RestController
public class PostController {


    private PostService postService;

    @Autowired
    PostController(PostService postService){
        this.postService = postService;
    }


    //클라 세션 - userId 겟
    //보완: 'getSessionUserId' 메서드 중복 해결 -> 인증 담당 auth라인에서 처리하기
    private Long getSessionUserId(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null){
            return null;
        }

        return (Long) session.getAttribute("userId");
    }



    //게시글 - 추가
    @PostMapping("/posts")
    public ResponseDto createPost(@RequestBody PostDto postDto, HttpServletRequest request){


        Long sessionUserId = getSessionUserId(request);

        //로그인 여부 필터링
        //보완: ㄴ> create 정책 = 서비스에서 담당해야.
        if(sessionUserId == null){
            return new ResponseDto("로그인이 필요합니다");
        }else if(postDto.validation()){
            return new ResponseDto("유효한 데이터를 입력해주세요");
        }else {
            postService.createPost(postDto, sessionUserId);
            return new ResponseDto("post_success");
        }

    }




    //게시글 - 전체조회
    @GetMapping("/posts")
    public ResponseDto viewPosts(
            //@RequestParam(required = false, defaultValue = "defaultTitle") String title,
            @RequestParam(required = true, defaultValue = "1") String page,
            @RequestParam(required = true, defaultValue = "5") Long size
    ){

        PostListDto postListDto = postService.viewPosts(page, size);


        ResponseDto responseDto = new ResponseDto(postListDto);
        responseDto.setMessage("success");


        //System.out.println(postDtoList);
        return responseDto; //회고: dto클래스 private필드인데 getter없어서 json자동변환 과정에서 바인딩 오류 뜸


    }





    //게시글 - 상세조회
    @GetMapping("posts/{postId}")
    public ResponseDto viewOnePost(
            @PathVariable Long postId,
            HttpServletRequest request
    ){

        Long sessionUserId = getSessionUserId(request);

        PostPageDto postPageDto = postService.viewOnePost(postId, sessionUserId);

        //권한 필터링
        if(postPageDto == null){
            return new ResponseDto("접근 권한이 없습니다");
        }

        ResponseDto responseDto = new ResponseDto(postPageDto);
        responseDto.setMessage("success");

        return responseDto;


    }








    //게시글 - 수정
    @PatchMapping("/posts/{postId}")
    public ResponseDto updatePost(
            @RequestBody PostDto postDto,
            @PathVariable Long postId,
            HttpServletRequest request){

        Long sessionUserId = getSessionUserId(request);

        //접근 권한 필터링
        if(postService.updatePost(postDto, postId, sessionUserId)){
            return new ResponseDto("접근 권한이 없습니다."); // -> 상태코드로 처리 필요
        }

        return new ResponseDto("update_success");

    }



    //게시글 - 삭제
    @DeleteMapping("/posts/{postId}")
    public ResponseDto deletePost(@PathVariable Long postId, HttpServletRequest request){


        Long sessionUserId = getSessionUserId(request);

        //접근 권한 필터링
        if(postService.deletePost(postId, sessionUserId)){
            return new ResponseDto("접근 권한이 없습니다."); // -> 상태코드로 처리 필요
        }

        return new ResponseDto("delete_success");


    }




    @GetMapping("/test")
    public void testCreatePost(){
        postService.testCreatePost();
    }












    /*

    <구현 관점>

    게시글 추가 () / userId
    게시글 전체조회 ()

    게시글 상세조회 (postId)
        ㄴ댓글 조회 (postId)

    게시글 수정, 삭제 (postId) / userId
    댓글 추가, 수정, 삭제 (postId) (commentId) / userId


     */


    /*

    <페이지 레이어 관점>

    //게시글 전체조회

    //게시글 추가

    //----------------------------------------------------------------------

    //게시글 상세조회

    //----------------------------------------------------------------------

    //게시글 수정, 삭제
    //댓글 등록, 수정, 삭제

     */



}
