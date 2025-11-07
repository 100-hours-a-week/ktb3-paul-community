package com.example.newCommuniryService01.Repository;

import com.example.newCommuniryService01.Domain.CommentDomain;
import com.example.newCommuniryService01.Domain.UserDomain;
import com.example.newCommuniryService01.Dto.CommentDto;
import com.example.newCommuniryService01.Dto.PostDto;
import com.example.newCommuniryService01.Dto.UserDto;

import java.util.List;

public interface UserRepository {



    public UserDomain save(UserDomain userDomain);

    public List<UserDomain> findAll();
    //id로 회원 조회
    public UserDomain findById(Long userId);

    public UserDomain findByEmail(String email);


    public UserDomain update(UserDomain userDomain, Long userId);

    public UserDomain delete(Long userId);






}

