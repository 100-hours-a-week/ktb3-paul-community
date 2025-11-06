package com.example.newCommuniryService01.Repository;

import com.example.newCommuniryService01.Domain.PostDomain;
import com.example.newCommuniryService01.Domain.PostUpdateDomain;
import com.example.newCommuniryService01.Domain.UserDomain;
import com.example.newCommuniryService01.Dto.CommentDto;
import com.example.newCommuniryService01.Dto.PostDto;
import com.example.newCommuniryService01.Dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class PostMemoryRepository implements PostRepository{


    private static Map<Long, PostDomain> dbMap = new HashMap<>();
    private static Long sequence = 0L;




    @Override
    public PostDomain save(PostDomain postDomain) {

        //피드백, 보완: 동시성 문제 대처
        postDomain.setId(++sequence);
        dbMap.put(postDomain.getId(), postDomain);

        return postDomain;
    }




    @Override
    public List<PostDomain> findAll(String page, Long size) {

        //return this.dbMap;
        return null;
    }

    @Override
    public PostDomain findById(Long postId) {

        return dbMap.get(postId);

    }

    //postId로 userId 가져오기
    public Long getUserId(Long postId){

        PostDomain postDomain = dbMap.get(postId);

        return postDomain.getUserId();
    }


    @Override
    public PostDomain update(PostUpdateDomain postUpdateDomain, Long postId) {


        //dbMap.put(postId, postDomain);

        return null;
    }



    @Override
    public PostDomain delete(Long postId) {
        dbMap.remove(postId);
        return null;
    }











}
