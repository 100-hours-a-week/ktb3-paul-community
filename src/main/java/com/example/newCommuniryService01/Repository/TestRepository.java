package com.example.newCommuniryService01.Repository;

import com.example.newCommuniryService01.Domain.PostDomain;
import com.example.newCommuniryService01.Domain.TestDomain;
import com.example.newCommuniryService01.Service.PostService;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class TestRepository {
    
    

    
    @PersistenceContext //-> 컨피그 - 생성자 주입도 가능
    //@AutoWired보다 안정적
    // why: Jpa전용 어노테이션, 매 요청마다 새 em 주입 -> 스레드 안정성 & 트랜잭션 자동처리 프록시
    EntityManager em;




    public TestDomain findById(Long id){

        return em.find(TestDomain.class, id);

    }

    public TestDomain findAll(){
        return null;
    }


    public TestDomain findByEmail(){
        return null;
    }




    public TestDomain save(TestDomain testDomain){
        em.persist(testDomain);
        return testDomain;
    }













    //코드실수: 기본키 자동생성인데 id 직접 할당함 -> 예외발생
    public void testCreatePost(){

        TestDomain testDomain = new TestDomain();
        testDomain.setTitle("hi22");
        testDomain.setContent("it's me22");

        em.persist(testDomain);



    }
    
}
