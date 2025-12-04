package com.example.newCommuniryService01.Service;


import com.example.newCommuniryService01.Domain.UserDomain;
import com.example.newCommuniryService01.Dto.UserDto;
import com.example.newCommuniryService01.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //유저 - 회원가입
    public UserDto signUp(UserDto userDto){

        // Dto -> Domain 변환
        UserDomain userDomain = userDto.toDomain();

        String encoded = passwordEncoder.encode(userDomain.getPassWord());
        userDomain.setPassWord(encoded);

        UserDomain Saved = userRepository.save(userDomain);

        // Domain -> Dto변환
        UserDto userDtoReturn = Saved.toDto();

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
