package com.example.newCommuniryService01.Repository;

import com.example.newCommuniryService01.Domain.PostDomain;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;



public interface PostJpaRepository extends JpaRepository<PostDomain, Long>{


    Optional<PostDomain> findByAuthor(String author);









}
