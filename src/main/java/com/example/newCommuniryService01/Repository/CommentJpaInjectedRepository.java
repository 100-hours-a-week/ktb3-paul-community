package com.example.newCommuniryService01.Repository;

import com.example.newCommuniryService01.Domain.CommentDomain;
import com.example.newCommuniryService01.Domain.CommentUpdateDomain;
import com.example.newCommuniryService01.Domain.UserDomain;
import com.example.newCommuniryService01.Dto.CommentDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CommentJpaInjectedRepository implements CommentRepository{


    private CommentJpaRepository commentJpaRepository;

    @Autowired
    public CommentJpaInjectedRepository(CommentJpaRepository commentJpaRepository){
        this.commentJpaRepository = commentJpaRepository;
    }



    @Override
    public CommentDomain save(CommentDomain commentDomain, Long postId) {
        commentJpaRepository.save(commentDomain);
        return null;
    }



    @Override
    public List<CommentDomain> findAll(Long postId) {
        return commentJpaRepository.findAll();
    }



    @Override
    public CommentDomain findById(Long id) {
        return commentJpaRepository.findById(id).get();
    }


    @Override
    public CommentDomain update(CommentUpdateDomain cud, Long commentId) {
        CommentDomain foundEntity = commentJpaRepository.findById(commentId).get();

        foundEntity.updateEntity(cud);

        return foundEntity;
    }

    @Override
    public CommentDomain delete(Long commentId) {
        CommentDomain foundEntity = commentJpaRepository.findById(commentId).get();
        commentJpaRepository.delete(foundEntity);
        return foundEntity;
    }









    @Override
    public Long getUserId(Long postId) {
        return 0L;
    }


    @Override
    public List<CommentDto> listingComment(Long postId) {
        return List.of();
    }





}
