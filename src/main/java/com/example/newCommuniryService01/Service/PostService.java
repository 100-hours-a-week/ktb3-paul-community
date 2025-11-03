package com.example.newCommuniryService01.Service;


import com.example.newCommuniryService01.Domain.PostDomain;
import com.example.newCommuniryService01.Dto.CommentDto;
import com.example.newCommuniryService01.Dto.PostDto;
import com.example.newCommuniryService01.Dto.PostListDto;
import com.example.newCommuniryService01.Dto.PostPageDto;
import com.example.newCommuniryService01.Repository.CommentRepository;
import com.example.newCommuniryService01.Repository.PostRepository;
import com.example.newCommuniryService01.Repository.TestRepository;
import com.example.newCommuniryService01.Strategy.Policy.PostPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class PostService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;
    private List<PostPolicy> policyStrategies;


    @Autowired
    TestRepository testRepository;



    //점검: @AW -> List<PostPolicy>
    @Autowired
    public PostService(PostRepository postRepository, CommentRepository commentRepository, List<PostPolicy> policyStrategies){

        this.policyStrategies = policyStrategies;

        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }



    //전략매칭 공통 메서드
    //코드체크: 널포인트 예외 체크 (매칭되는 전략 못찾으면 예외터짐)
    public PostPolicy getStrategy(Long sessionUserId){

        //변수명 변경: policyStrategy -> postPolicy
        for(PostPolicy policyStrategy : policyStrategies) {
            if (policyStrategy.matchStrategy(sessionUserId)) {
                return policyStrategy;
            }
        }

        return null;
    }




    //게시글 - 추가
    //부여할 조회권한별 분기 필요 (관리자에게만, 로그인에게만, 모두에게, +나에게만) => dto단에서 열거형으로 처리
    public PostDto createPost(PostDto postDto, Long sessionUserId){

        /* (전략패턴 적용전 버전)
        //세션 매치해서 가져온 userId 할당
        postDto.setUserId(sessionUserId);


        PostDomain postDomain = postDto.toDomain();
        return (postRepository.save(postDomain)).toDto();

         */


        return getStrategy(sessionUserId).createPost(postDto, sessionUserId);

    }



    //전략패턴 미적용 (전략무관 동일 응답)
    //게시글 - 전체조회
    public PostListDto viewPosts(String page, Long size){

        Map<Long, PostDomain> postDbMap = postRepository.findAll(page, size);
        List<PostDto> postDtoList = new ArrayList<>();

        for (PostDomain postDomain : postDbMap.values()) {
            PostDto postDto = postDomain.toDto();
            postDtoList.add(postDto);
        }

        System.out.println("전체조회: "+postDtoList);

        return new PostListDto(postDtoList);
    }





    //게시글 - 상세조회
    public PostPageDto viewOnePost(Long postId, Long sessionUserId){

        /* (전략패턴 적용전 버전)

        //게시글 객체 겟
        PostDomain postDomain = postRepository.findById(postId);

        //댓글 리스트화 (postId)
        List<CommentDto> commentDomainList = commentRepository.listingComment(postId);

        //응답 Dto에 담기
        PostPageDto postPageDto = new PostPageDto();

        postPageDto.setPostDto(postDomain.toDto());
        postPageDto.setCommentDtoList(commentDomainList);


        return postPageDto;

         */

        //보완 여지: 권한과 관련된 요소들(전략 매칭, 권한 필터링)은 auth라인에서 처리하고 post라인에서 가져다 쓰도록 수정?
        return getStrategy(sessionUserId).viewOnePost(postId, sessionUserId);

    }





    //게시글 - 수정
    public Boolean updatePost(PostDto postDto, Long postId, Long sessionUserId){

        /* 전략패턴 적용전 버전
        //접근 권한 필터링
        if(!sessionUserId.equals(postRepository.getUserId(postId))){
            return true;
        }

        //메인 수정작업 (-> 도메인과 Dto객체 내부 메서드 - PUT에서 PATCH로 수정 필요)
        postDto.setId(postId);
        postDto.setUserId(postRepository.findById(postId).getUserId());
        postDto.setAuthor(postRepository.findById(postId).getAuthor());
        //
        postRepository.update(postDto.toDomain(), postId);


        return false;

         */

        return getStrategy(sessionUserId).updatePost(postDto, postId, sessionUserId);

        //PATCH화
        /*
        1) Dto객체(3상태) 겟 - 변경된 필드 파악
        2) 리포에서 도메인 객테 겟 - 세터로 일부 필드 셋 후 리포 저장
         */

    }






    //게시글 - 삭제
    public Boolean deletePost(Long postId, Long sessionUserId){

        /* 전략패턴 적용전 버전
        //접근 권한 필터링
        if(!sessionUserId.equals(postRepository.getUserId(postId))){
            return true;
        }

        //메인 삭제작업
        PostDomain postDomain = postRepository.delete(postId);

        return false;

         */

        return getStrategy(sessionUserId).deletePost(postId, sessionUserId);


    }









    public void testCreatePost(){

        testRepository.testCreatePost();

    }








}
