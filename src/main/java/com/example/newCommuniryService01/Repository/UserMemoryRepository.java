package com.example.newCommuniryService01.Repository;


import com.example.newCommuniryService01.Domain.UserDomain;
import com.example.newCommuniryService01.Dto.CommentDto;
import com.example.newCommuniryService01.Dto.PostDto;
import com.example.newCommuniryService01.Dto.UserDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class UserMemoryRepository implements UserRepository{


    //빈객체로 한개만 생성되는데 static을 쓰는 이유는?
    private static Map<Long, UserDomain> dbMap = new HashMap<>();
    private static Long sequence = 0L;



    //회원가입
    @Override
    public UserDomain save(UserDomain userDomain) {
        userDomain.setId(++sequence);
        dbMap.put(userDomain.getId(), userDomain);

        System.out.println("리포지토리, 저장됨: " + userDomain);

        return userDomain;
    }

    @Override
    public List<UserDomain> findAll() {
        return List.of();
    }


    //id로 회원 조회
    public UserDomain findById(Long userId){

        UserDomain userDomain = dbMap.get(userId);

        return userDomain;
    }



    //로그인
    @Override
    public UserDomain findByEmail(String email) {


        //피드백, 보완: 순회 성능 문제 개선
        for(UserDomain userDomain : dbMap.values()){
            if(userDomain.getEmail().equals(email)){
                return userDomain;
            }
        }
        return null;
    }





    @Override
    public UserDomain update(UserDomain userDomain, Long userId) {
        return null;
    }

    @Override
    public UserDomain delete(Long userId) {
        return null;
    }


}
