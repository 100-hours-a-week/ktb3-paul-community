package com.example.newCommuniryService01;


import com.example.newCommuniryService01.Repository.PostMemoryRepository;
import com.example.newCommuniryService01.Repository.PostRepository;
import com.example.newCommuniryService01.Repository.TestRepository;
import com.example.newCommuniryService01.Service.PostService;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {


    //private final PostRepository postRepository;

    /*
    @Autowired
    SpringConfig(PostRepository postRepository){
        this.postRepository = postRepository;
    }

     */



    /*
    @Bean
    public PostService postService(){
        return new PostService(postRepository);
    }

     */



    /*
    @Bean
    public PostRepository postRepository(){

        //return new PostMemoryRepository();
        //return new TestRepository();
        return null;
    }

     */



    

}
