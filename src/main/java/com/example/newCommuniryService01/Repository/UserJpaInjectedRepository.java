package com.example.newCommuniryService01.Repository;

import com.example.newCommuniryService01.Domain.PostDomain;
import com.example.newCommuniryService01.Domain.UserDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserJpaInjectedRepository implements UserRepository{


    private UserJpaRepository userJpaRepository;

    @Autowired
    public UserJpaInjectedRepository(UserJpaRepository userJpaRepository){
        this.userJpaRepository = userJpaRepository;
    }




    @Override
    public UserDomain save(UserDomain userDomain) {
        return userJpaRepository.save(userDomain);
    }



    @Override
    public List<UserDomain> findAll() {
        return userJpaRepository.findAll();
    }

    @Override
    public UserDomain findById(Long userId) {
        return userJpaRepository.findById(userId).get();
    }

    @Override
    public UserDomain findByEmail(String email) {
        return userJpaRepository.findByEmail(email).get();
    }




    @Override
    public UserDomain update(UserDomain userDomain, Long userId) {

        UserDomain foundEntity = userJpaRepository.findById(userId).get();

        //foundEntity.updateEntity(uud);

        return foundEntity;
    }



    @Override
    public UserDomain delete(Long userId) {

        UserDomain foundEntity = userJpaRepository.findById(userId).get();
        userJpaRepository.delete(foundEntity);
        return foundEntity;

    }







}
