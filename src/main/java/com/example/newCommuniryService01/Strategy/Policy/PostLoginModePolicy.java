package com.example.newCommuniryService01.Strategy.Policy;

import com.example.newCommuniryService01.Domain.PostAuthority;
import com.example.newCommuniryService01.Domain.PostDomain;
import com.example.newCommuniryService01.Domain.UserMode;
import com.example.newCommuniryService01.Dto.CommentDto;
import com.example.newCommuniryService01.Dto.PostDto;
import com.example.newCommuniryService01.Dto.PostListDto;
import com.example.newCommuniryService01.Dto.PostPageDto;
import com.example.newCommuniryService01.Repository.CommentRepository;
import com.example.newCommuniryService01.Repository.PostRepository;
import com.example.newCommuniryService01.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.example.newCommuniryService01.Domain.PostAuthority.NORMAL;

@Component
public class PostLoginModePolicy implements PostPolicy{

    private UserRepository userRepository;
    private PostRepository postRepository;
    private CommentRepository commentRepository;


    @Autowired
    public PostLoginModePolicy(PostRepository postRepository, CommentRepository commentRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }


    //전략매칭 - 내 모드 찾기 메서드(세션유저 id확인) = "로그인유저 모드로 전환"
    //보완: 열거형으로 변경하기
    public Boolean matchStrategy(Long sessionUserId){

        if(userRepository.findById(sessionUserId).getUserMode().equals(UserMode.NORMAL)){
            return true;
        }

        return false;

    }








    @Override
    public PostAuthority fetchPostAuthorityData(PostDto postDto, Long sessionUserId) {

        return PostAuthority.NORMAL;

    }

    @Override
    public Boolean verifyAuthority(Long postId, Long sessionUserId){


        if(postRepository.findById(postId).getPostAuthority().equals(PostAuthority.NORMAL)){
            return true; //맞을 경우 true 반환
        }

        return false;

    }


    @Override
    public Boolean checkUnauthorized(Long postId, Long sessionUserId, PostDto postDto){

        if(!postRepository.findById(postId).getPostAuthority().equals(PostAuthority.NORMAL)){
            return true; //아닐 경우 true 반환
        }

        return false;

    }
    @Override
    public Boolean checkUnauthorized(Long postId, Long sessionUserId){

        if(!postRepository.findById(postId).getPostAuthority().equals(PostAuthority.NORMAL)){
            return true; //아닐 경우 true 반환
        }

        return false;

    }





























    //추가 (일반유저용 추가 작업)
    @Override
    public PostAuthority createPost(PostDto postDto, Long sessionUserId) {

        /* 진정한 DIP 이전 버전

        //세션 매치해서 가져온 userId 할당
        postDto.setUserId(sessionUserId);


        PostDomain postDomain = postDto.toDomain();
        return (postRepository.save(postDomain)).toDto();

         */
        return PostAuthority.NORMAL;
    }


    //전체조회
    @Override
    public PostListDto viewPosts(String page, Long size) {
        return null;
    }




    //상세조회
    @Override
    public Boolean viewOnePost(Long postId, Long sessionUserId){

        /* 진정한 DIP 이전 버전

        //보완: 반환값 false도 가능하게 Object로 설정 후 필터링 조건문 일관화
        if(!postRepository.findById(postId).getAdminOnly()){
            //게시글 객체 겟
            PostDomain postDomain = postRepository.findById(postId);
            //댓글 리스트화 (postId)
            List<CommentDto> commentDomainList = commentRepository.listingComment(postId);

            //응답용 PostPageDto에 담기
            PostPageDto postPageDto = new PostPageDto();
            postPageDto.setPostDto(postDomain.toDto());
            postPageDto.setCommentDtoList(commentDomainList);

            //return postPageDto;
            return null;
        }

        System.out.println("로그인유저 모드에서: 접근 권한이 없습니다");

        return null;

         */

        if(postRepository.findById(postId).getPostAuthority().equals(PostAuthority.NORMAL)){

            return true;
        }


        System.out.println("일반유저 모드에서: 접근 권한이 없습니다");

        return false;


    }


    //수정
    @Override
    public Boolean updatePost(PostDto postDto, Long postId, Long sessionUserId){

        if(!postRepository.findById(postId).getPostAuthority().equals(PostAuthority.NORMAL)){
            return true; //아닐 경우 true 반환 (접근 불가)
        }

        return false;


        /* 진정한 DIP 이전 버전

        //접근 권한 필터링
        if(postRepository.findById(postId).getAdminOnly()){
            return true; //adminOnly일 경우 true 반환 (접근 불가)
        }

        //메인 수정작업 (-> 도메인과 Dto객체 내부 메서드 - PUT에서 PATCH로 수정 필요)
        postDto.setId(postId);
        postDto.setUserId(postRepository.findById(postId).getUserId());
        postDto.setAuthor(postRepository.findById(postId).getAuthor());
        //
        //postRepository.update(postDto.toDomain(), postId);


        return false;

         */
    }



    //삭제
    @Override
    public Boolean deletePost(Long postId, Long sessionUserId) {

        /* 구 버전

        //접근 권한 필터링
        if(postRepository.findById(postId).getAdminOnly()){
            return true;
        }

        //메인 삭제작업
        PostDomain postDomain = postRepository.delete(postId);

        return false;

        */

        if(!postRepository.findById(postId).getPostAuthority().equals(PostAuthority.NORMAL)){
            return true;
        }

        return false;


    }

}
