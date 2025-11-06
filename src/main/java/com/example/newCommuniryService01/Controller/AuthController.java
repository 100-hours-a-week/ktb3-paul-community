package com.example.newCommuniryService01.Controller;

import com.example.newCommuniryService01.Dto.ResponseDto;
import com.example.newCommuniryService01.Dto.UserDto;
import com.example.newCommuniryService01.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {



    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    //인증 - 로그인
    @PostMapping("/auth")
    public ResponseDto signIn(@RequestBody UserDto userDto, HttpServletRequest request){

        /*
        1) 이메일, 패스워드 검증
        2) 고유 세션ID 발급 - (쿠키로)전송

        3) (쿠키로) 클라가 보낸 세션ID - 세션 객체에서 조회 > 유저ID
         */



        //로그인 검증 - userId 겟
        Long userIdGotten = authService.signIn(userDto);


        if(userIdGotten == null){

            return new ResponseDto("로그인 실패");

        }else{

            //기존세션 파기 & 세션 발급
            HttpSession old = request.getSession(false);
            if (old != null) old.invalidate();

            HttpSession session = request.getSession(true);
            session.setAttribute("userId", userIdGotten);

            return new ResponseDto("로그인 성공");

            //request.changeSessionId();

        }
    }


    //인증 - 로그아웃
    @DeleteMapping("auth")
    public ResponseDto signOut(HttpServletRequest request){

        request.getSession().invalidate();

        return new ResponseDto("로그아웃 완료");
    }






    public static Long getSessionUserId(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null){
            return null;
        }

        return (Long) session.getAttribute("userId");
    }













}
