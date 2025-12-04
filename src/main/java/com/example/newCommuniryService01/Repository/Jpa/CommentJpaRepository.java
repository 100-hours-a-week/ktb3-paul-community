package com.example.newCommuniryService01.Repository.Jpa;

import com.example.newCommuniryService01.Domain.CommentDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentDomain, Long> {

}
