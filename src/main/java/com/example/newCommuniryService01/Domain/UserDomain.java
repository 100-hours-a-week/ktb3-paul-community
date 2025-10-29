package com.example.newCommuniryService01.Domain;

import com.example.newCommuniryService01.Dto.UserDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@SequenceGenerator(
        name = "user_seq",
        sequenceName = "user_seq",
        allocationSize = 50             //-> DB에서 키 여러개 미리 가져와두고, 메모리에서 빠르게 할당
)
public class UserDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id = 0L;
    
    private String userName;
    private String email;
    private String passWord;

    private Boolean adminYN = false;


    public UserDomain(String userName, String email, String passWord, Boolean adminYN){
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
        this.adminYN = adminYN;
    }



    //Domain - Dto 변환
    /*
    회고: toDto 전용 클래스 -> toDto작업 추상화하기 (user,post,comment 객체들)
     */
    public UserDto toDto() {
        return new UserDto(
                this.userName,
                this.email,
                null,
                this.adminYN
        );
    }





    @Override
    public String toString() {
        return "UserDomain{userName='" + userName + "', email='" + email + "'}";

    }

}
