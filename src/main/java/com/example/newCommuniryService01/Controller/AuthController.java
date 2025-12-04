package com.example.newCommuniryService01.Controller;

import com.example.newCommuniryService01.Dto.ResponseDto;
import com.example.newCommuniryService01.Dto.SignInDto;
import com.example.newCommuniryService01.Dto.UserDto;
import com.example.newCommuniryService01.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {



    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService){
        this.authService = authService;
    }


    @GetMapping("/auth")
    public ResponseDto getSignInPage(){

        System.out.println("로그인화면 반환");

        return new ResponseDto("로그인 화면 반환");
    }


    @PatchMapping("/auth")
    public ResponseDto test_patchAuth(){

        System.out.println("/auth - patch: 수정 실패");

        return new ResponseDto("auth - patch: 수정 실패");
    }


    @GetMapping("/default")
    public ResponseDto defaultPage(){

        System.out.println("기본 페이지 반환");

        return new ResponseDto("기본 페이지 반환");
    }



    @GetMapping("/auth/check")
    public ResponseDto authCheck(Authentication authentication){

        System.out.println("authChecking. . .");

        //System.out.println(">> Principal  : " + authentication.getPrincipal());
        System.out.println(">> Username   : " + authentication.getName());
        System.out.println(">> Authorities: " + authentication.getAuthorities());
        System.out.println(">> Credentials: " + authentication.getCredentials()); // 보통 null


        return new ResponseDto("auth checking");
    }





    //API 방식 인증흐름 구현
    /*
    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/auth-security")
    public ResponseDto login(@RequestBody SignInDto dto) {



        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassWord())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        return new ResponseDto("login_success");
    }

     */











    //인증 - 로그인
    @PostMapping("/auth")
    public ResponseDto signIn(@RequestBody UserDto userDto, HttpServletRequest request){

        /*
        1) 이메일, 패스워드 검증
        2) 고유 세션ID 발급 - (쿠키로)전송

        3) (쿠키로) 클라가 보낸 세션ID - 세션 객체에서 조회 > 유저ID
         */


        
        System.out.println("userDto: "+userDto.getEmail()+userDto.getPassWord());

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

            System.out.println("로그인 성공, 세션유저Id: "+AuthController.getSessionUserId(request));
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
