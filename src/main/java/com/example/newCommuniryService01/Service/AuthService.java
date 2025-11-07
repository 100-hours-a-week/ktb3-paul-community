package com.example.newCommuniryService01.Service;

import com.example.newCommuniryService01.Domain.UserDomain;
import com.example.newCommuniryService01.Dto.UserDto;
import com.example.newCommuniryService01.Repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Long signIn(UserDto userDto){


        UserDomain userDomainGotten = userRepository.findByEmail(userDto.getEmail());

        //이메일 못찾음
        if(userDomainGotten == null){
            //"이메일이 존재하지 않습니다"
            System.out.println("userDomainGotten = null");
        }

        //이메일 찾음 - 비밀번호 확인
        if(userDomainGotten.getPassWord().equals(userDto.getPassWord())){
            return userDomainGotten.getId();
        }

        return null;
    }




}
