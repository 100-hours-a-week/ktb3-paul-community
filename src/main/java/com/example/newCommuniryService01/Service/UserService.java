package com.example.newCommuniryService01.Service;


import com.example.newCommuniryService01.Domain.UserDomain;
import com.example.newCommuniryService01.Dto.UserDto;
import com.example.newCommuniryService01.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //유저 - 회원가입
    //-> 유저 종류별 분기 필요
    public UserDto signUp(UserDto userDto){

        //1) Dto -> Domain 변환
        UserDomain userDomain = userDto.toDomain();

        //2) Domain로직(정책) 메서드 실행


        //3) Domain객체 전달
        UserDomain userDomainSaved = userRepository.save(userDomain);

        //4) Domain -> Dto변환
        UserDto userDtoReturn = userDomainSaved.toDto();

        return userDtoReturn;


    }


    //임시
    public UserDto signUpJpa(UserDto userDto){

        /*
        UserDomain userDomain = new UserDomain(
                userDto.getUserName(),
                userDto.getEmail(),
        )

         */
        return null;
    }








}
