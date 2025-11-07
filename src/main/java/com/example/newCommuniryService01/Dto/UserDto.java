package com.example.newCommuniryService01.Dto;


import com.example.newCommuniryService01.Domain.UserDomain;
import com.example.newCommuniryService01.Domain.UserMode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.BindException;

@Getter
@Setter
public class UserDto {

    private Long id;

    private String userName;
    private String email;
    private String passWord;

    private Boolean adminYN = false;
    private UserMode userMode = null;



    public UserDto(
            String userName,
            String email,
            String passWord,
            Boolean adminYN,
            UserMode userMode){
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;

        this.adminYN = adminYN;
        this.userMode= userMode;
    }
















    //Dto - Domain 변환
    public UserDomain toDomain() {
        return new UserDomain(
                this.userName,
                this.email,
                this.passWord,

                this.adminYN,
                this.userMode
        );
    }



    /*
    검증 후 status코드 전송 방법
    1. @Valid 어노테이션 사용 (NotBlank/Email/Size/...)
    2. 직접 BindException 던지기 (=400)
     */

    //입력값 '유효성' 검증 - Dto
    public void validation(){

        if (email == null) {
            //throw new BindException();
        }
        if (passWord == null) {
            //throw new BindException();
        }


    }




    /*
    @Override
    public String toString() {
        return "UserDto{userName='" + userName + "', email='" + email + "'}";

    }

     */
}
