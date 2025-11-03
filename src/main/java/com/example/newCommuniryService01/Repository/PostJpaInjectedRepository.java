package com.example.newCommuniryService01.Repository;

import com.example.newCommuniryService01.Domain.PostDomain;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Map<Long, PostDomain> findAll(String page, Long size) {
        postJpaRepository.findAll();

        //리스트 -> 맵으로 변환


        return Map.of();
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
    public PostDomain update(PostDomain postDomain, Long postId) {


        PostDomain found = postJpaRepository.findById(postId).get();

        found.updateDomain(postDomain);

        return found;
    }

    @Override
    public PostDomain delete(Long postId) {
        return null;
    }






}
