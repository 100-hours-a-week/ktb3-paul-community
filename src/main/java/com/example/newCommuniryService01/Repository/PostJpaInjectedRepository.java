package com.example.newCommuniryService01.Repository;

import com.example.newCommuniryService01.Domain.PostDomain;
import com.example.newCommuniryService01.Domain.PostUpdateDomain;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Transactional
public class PostJpaInjectedRepository implements PostRepository{


    private PostJpaRepository postJpaRepository;

    @Autowired
    public PostJpaInjectedRepository(PostJpaRepository postJpaRepository){

        //자동 생성된 Jpa리포의 기본 구현체가 할당됨
        this.postJpaRepository = postJpaRepository;
    }




    @Override
    public PostDomain save(PostDomain postDomain) {

        return postJpaRepository.save(postDomain);
    }

    @Override
    public List<PostDomain> findAll(String page, Long size) {

        return postJpaRepository.findAll();

    }


    @Override
    public PostDomain findById(Long id) {

        Optional<PostDomain> found = postJpaRepository.findById(id);
        return found.orElse(null);
    }


    @Override
    public Long getUserId(Long postId) {
        return postJpaRepository.findById(postId).get().getUserId();
    }


    @Override
    public PostDomain update(PostUpdateDomain pud, Long postId) {


        PostDomain foundEntity = postJpaRepository.findById(postId).get();

        foundEntity.updateEntity(pud);

        return foundEntity;
    }



    @Override
    public PostDomain delete(Long postId) {

        PostDomain foundEntity = postJpaRepository.findById(postId).get();
        postJpaRepository.delete(foundEntity);
        return foundEntity;
    }






}
