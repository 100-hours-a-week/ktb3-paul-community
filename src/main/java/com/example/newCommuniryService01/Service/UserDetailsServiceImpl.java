package com.example.newCommuniryService01.Service;

import com.example.newCommuniryService01.Domain.UserDomain;
import com.example.newCommuniryService01.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{



    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        UserDomain userDomain = userRepository.findByEmail(username);

        if(userDomain == null){
            throw new UsernameNotFoundException("해당 이메일의 유저 없음: "+username);
        }


        System.out.println("유디서 - LUBUn메서드: "+userDomain.getEmail()+userDomain.getPassWord()+"ROLE_"+userDomain.getUserMode().name());

        return new org.springframework.security.core.userdetails.User(
                userDomain.getEmail(),
                userDomain.getPassWord(),
                List.of(new SimpleGrantedAuthority("ROLE_"+userDomain.getUserMode().name()))
        );
    }







}
