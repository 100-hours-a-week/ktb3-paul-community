package com.example.newCommuniryService01;

import com.example.newCommuniryService01.Domain.PostDomain;
import com.example.newCommuniryService01.Dto.PostDto;
import com.example.newCommuniryService01.Repository.PostJpaRepository;
import com.example.newCommuniryService01.Service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class newCommuniryService01ApplicationTests {

    //@Autowired
    //PostJpaRepository postJpaRepository;

    @Autowired
    PostService postService;



	@Test
	void contextLoads() {
	}


    /*
    void testJpa(){

        PostDomain postDomain = new PostDomain(1L,1L,"paul","hi","nice",false);

        postJpaRepository.save(postDomain);

    }

     */

    @Test
    void 리포지토리_우선순위_테스트(){


        PostDto postDto = new PostDto(1L,1L,"paul","hi","nice",false);

        this.postService.createPost(postDto, 1L);

        this.postService.viewPosts("1",1L);


    }

}
