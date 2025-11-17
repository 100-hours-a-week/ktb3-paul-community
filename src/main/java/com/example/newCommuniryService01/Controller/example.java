package com.example.newCommuniryService01.Controller;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class example {

    @GetMapping("/UI_test2")
    public String test(){

        return "test2";
    }


    //----------------------------

    @GetMapping("/UI_signIn")
    public String signIn(){

        return "signIn";
    }


    @GetMapping("/UI_signUp")
    public String signUp(){

        return "signUp";
    }

    @GetMapping("/UI_userUpdate")
    public String userUpdate(){

        return "userUpdate";
    }


}
