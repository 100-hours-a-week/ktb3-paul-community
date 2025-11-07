package com.example.newCommuniryService01.Service;


import com.example.newCommuniryService01.Domain.CommentDomain;
import com.example.newCommuniryService01.Domain.PostDomain;
import com.example.newCommuniryService01.Domain.PostUpdateDomain;
import com.example.newCommuniryService01.Dto.*;
import com.example.newCommuniryService01.Repository.CommentRepository;
import com.example.newCommuniryService01.Repository.PostJpaInjectedRepository;
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

        PostDomain postDomain = new PostDomain(
                postDto.getId(),
                postDto.getUserId(),
                postDto.getAuthor(),
                postDto.getTitle(),
                postDto.getContent(),
                null,
                getStrategy(sessionUserId).fetchPostAuthorityData(postDto, sessionUserId) //전략구현체가 서비스에 포함되는 형태로 개선
                //ㄴ> 변경하기: 유저 종류에 따른 자동할당 => 유저가 직접 선택하도록
        );

        postRepository.save(postDomain);

        return null;

    }



    //전략패턴 미적용 (전략무관 동일 응답)
    //게시글 - 전체조회
    public PostListDto viewPosts(String page, Long size){

        /* Jpa 이전 버전

        Map<Long, PostDomain> postDbMap = postRepository.findAll(page, size);
        List<PostDto> postDtoList = new ArrayList<>();

        // 맵-리스트 변환
        for (PostDomain postDomain : postDbMap.values()) {
            PostDto postDto = postDomain.toDto();
            postDtoList.add(postDto);
        }

        System.out.println("전체조회: "+postDtoList);

        return new PostListDto(postDtoList);

         */

        List<PostDomain> postDomainList = postRepository.findAll(page, size);
        List<PostFetchDto> pfdList = new ArrayList<>();

        //domain - dto변환 -> fetch 정책 적용 (선택적 할당)
        for(PostDomain postDomain : postDomainList){
            PostFetchDto newPfd = new PostFetchDto(
                    postDomain.getId(),
                    postDomain.getUserId(),
                    postDomain.getAuthor(),
                    postDomain.getTitle(),
                    null,
                    postDomain.getAdminOnly(),
                    null
            );
            pfdList.add(newPfd);
        }


        System.out.println("전체조회: "+pfdList);

        return new PostListDto(pfdList);
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

        return getStrategy(sessionUserId).viewOnePost(postId, sessionUserId);

         */

        //보완 여지: 권한과 관련된 요소들(전략 매칭, 권한 필터링)은 auth라인에서 처리하고 post라인에서 가져다 쓰도록 수정?

        if(getStrategy(sessionUserId).verifyAuthority(postId, sessionUserId)){

            //단일 게시글 - dto변환/할당
            PostDomain postDomain = postRepository.findById(postId);

            PostDto postDto = new PostDto(
                    postDomain.getId(),
                    postDomain.getUserId(),
                    postDomain.getAuthor(),
                    postDomain.getTitle(),
                    postDomain.getContent(),
                    postDomain.getAdminOnly(),
                    postDomain.getPostAuthority()
            );


            //댓글리스트 - dto변환
            List<CommentDomain> commentDomainList = commentRepository.findAll(postId);
            List<CommentDto> commentDtoList = new ArrayList<>();

            for(CommentDomain commentDomain : commentDomainList){
                CommentDto commentDto = new CommentDto(
                        commentDomain.getId(),
                        commentDomain.getPostId(),
                        commentDomain.getUserId(),
                        commentDomain.getAuthor(),
                        commentDomain.getContent()
                );
                commentDtoList.add(commentDto);
            }


            //총 담기
            PostPageDto postPageDto = new PostPageDto(postDto, commentDtoList);

            return postPageDto;

        }

        return null;

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


        //Jpa 적용 + '진정한 DIP' 보완 버전
        if(getStrategy(sessionUserId).checkUnauthorized(postId, sessionUserId, postDto)){
            return true;
        }

        PostUpdateDomain pud = new PostUpdateDomain(
                postDto.getId(),
                postDto.getUserId(),
                postDto.getAuthor(),
                postDto.getTitle(),
                postDto.getContent(),
                null, // 서비스계층에서의 비즈니스로직 적용 예시 (dto/domain변환 - 선택적 필드 할당)
                null
        );

        postRepository.update(pud, postId);



        return false;


        //PATCH화
        /*
        1) Dto객체(3상태) 겟 - 변경된 필드 파악
        2) 리포에서 도메인 객테 겟 - 세터로 일부 필드 셋 후 리포 저장
         */

    }






    //게시글 - 삭제
    public Boolean deletePost(Long postId, Long sessionUserId){

        /* 전략패턴 적용 전 버전
        //접근 권한 필터링
        if(!sessionUserId.equals(postRepository.getUserId(postId))){
            return true;
        }

        //메인 삭제작업
        PostDomain postDomain = postRepository.delete(postId);

        return false;

         */

        if(getStrategy(sessionUserId).checkUnauthorized(postId, sessionUserId)){
            return true;
        }

        postRepository.delete(postId);
        return false;

    }









    public void testCreatePost(){

        testRepository.testCreatePost();

    }








}
