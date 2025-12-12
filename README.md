# 개요

## 프로젝트 소개

BookHub는 책과 인사이트, 삶에 대한 여러 이야기를 공유하며 독서와 성장에 더 몰입할 수 있도록 해주는 커뮤니티 서비스입니다.

## 사용한 기술

### **백엔드**

**언어 / 프레임워크**

- Java 17
- SpringBoot 3.5.6
- JPA(Spring Data JPA)
- Spring Security

**DB**

- H2 database

**Build Tool**

- Gradle 8.14.3

### **프론트엔드**

**언어**

- HTML / CSS
- JavaScript (fetch api)

## 주요 기능

### 총 기능 목록

**CRUD**

- 회원:
    - 가입 / 조회 / 수정 / 탈퇴
- 로그인:
    - 로그인 / 로그아웃
- 게시글:
    - 생성
    - 목록조회 / 상세조회
    - 수정 / 삭제
- 댓글:
    - 생성
    - 게시글 별 조회
    - 수정 / 삭제
- 좋아요 (추후 구현 예정)
    - 추가 / 취소

### 실행 영상

(추후 업로드 예정)

# 구조 및 설계

## 패키지 구조

```java
src
└── main
    └── java
        ├── Config               
        │
        ├── Controller           
        │
        ├── Domain               
        │
        ├── Dto                  
        │
        ├── Repository           
        │   ├── Jpa              
        │   └── Memory           
        │
        └── Service              
			└── Strategy         
```

## API

![image.png](attachment:964b6e1b-9f06-46f7-bd30-b5b2ed533bbd:image.png)

## DB

### ERD

![image.png](attachment:984b54a9-aa02-4ae4-9bc9-17887c0ba41b:image.png)

![image.png](attachment:7aa155bf-1b24-4b8c-bac8-24b8950b0dd3:image.png)

![image.png](attachment:4f9bb002-c3fc-4163-a243-85dabe886c8a:image.png)

![image.png](attachment:9eca62e6-40ac-4d53-a620-a26d1a8f512a:image.png)

![image.png](attachment:777952f0-a4a9-49c2-b7dc-2b81f44017fd:image.png)

# 개발 과정

## 1. 기본 API 및 MVC 구조 설계

프로젝트 초기 단계에서는 **HTTP 기반 CRUD API 구현**과 함께 스프링 MVC 구조를 명확히 나누는 데 집중했다.

- **Controller / DTO**: 요청 파싱, 검증, 전달
- **Service / Domain**: 비즈니스 로직 수행
- **Repository**: 영속성 접근만 담당
    

## 2. SOLID 적용을 통한 리팩터링 (전략 패턴 기반)

CRUD 권한 정책에 따라 서비스 로직이 복잡해지는 문제를 해결하기 위해 **전략 패턴**을 적용해 리팩터링을 진행했다.

### 문제점

- 정책이 늘어날 때마다 `PostService` 내부 조건문이 증가
- 코드 가독성 저하
- 정책 로직과 서비스 로직이 섞여 OCP·DIP 위반

### 해결 방식 (전략 패턴 적용)

- `PostService`는 “컨텍스트” 역할만 수행하도록 단순화
- `PostPolicy` 인터페이스(추상화 계층) 정의
- `AdminPolicy`, `UserPolicy` 등 정책 구현체 분리
- 서비스는 “어떤 정책을 쓸지”만 결정하고, 로직은 전략에 위임

### 리팩터링 성과

- **DIP 충족**: Service → Policy(추상화)에만 의존
- **OCP 충족**: 정책 추가/수정 시 Service 수정 없음
- **결합도 감소 & 응집도 증가**: 각 클래스는 한 역할만 담당
- **유지보수성·테스트 용이성 상승**

## 3. DB 설계 및 JPA 적용

### Spring Data JPA 내부 동작 원리 학습 및 적용

JPARepository를 사용하는 방식과 추상화 Repository를 함께 사용하는 방식 간의 차이를 학습하며, **프로젝트 내에서 가장 직관적인 구조**로 채택했다.

### DIP 보완

정책 구현체가 직접 Repository에 접근하던 초기 구조를 개선해

- Repository 의존성은 Service에서만 관리
- 정책은 Service의 기능을 호출만 하도록 구조 조정하여 계층 간 역할 분리가 더욱 명확해졌다.

## 4. Spring Security 기반 인증/인가 구현

인증/인가를 직접 구현하던 이전 구조에서 벗어나, 시큐리티의 **표준 인증 흐름**과 **인가 매커니즘**을 프로젝트에 적용

### 인증 구현

- 스프링 시큐리티 인증 흐름 내부 구조 이해
    
    (Authentication Filter → Authentication Manager → Provider → UserDetailsService)
    
- 인증 객체는 SecurityContextHolder → Session 저장 구조로 관리

### 인가 구현

기존의 “전략 패턴 기반 인가 로직” 대신 스프링 시큐리티의 표준 기능 사용:

- URL 기반 인가: `antMatchers`, `hasRole`, `authenticated()`
- 메서드 기반 인가: `@PreAuthorize`



## 후기

프로젝트 전반은 **기본적인 CRUD API → MVC 설계 → SOLID 리팩터링 → JPA 기반 도메인 모델링 → Spring Security 인증/인가**로 단계적으로 확장되며 진행되었고, 각 단계에서 **아키텍처 설계, 관심사 분리, 객체지향, 인증 구조, 트러블슈팅 역량**이 향상되었다.
