package com.example.newCommuniryService01.Repository.Jpa;

import com.example.newCommuniryService01.Domain.PostDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface PostJpaRepository extends JpaRepository<PostDomain, Long>{


    Optional<PostDomain> findByAuthor(String author);









}
