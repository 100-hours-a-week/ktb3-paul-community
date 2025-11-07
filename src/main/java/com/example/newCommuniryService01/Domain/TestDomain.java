package com.example.newCommuniryService01.Domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter
@Setter
/*@SequenceGenerator(
        name = "test_seq",
        sequenceName = "test_seq",
        allocationSize = 50             //-> allocationSize: DB에서 여러개의 키를 미리 가져와두고, 메모리에서 빠르게 할당
)
 */
public class TestDomain {

    //@Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_seq")
    private Long id = 0L;

    private String title = "";
    private String content = "";
}
