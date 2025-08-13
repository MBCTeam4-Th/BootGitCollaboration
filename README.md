* 기존 shop project 구조와 최대한 유사하게 작성함.


1. 프로젝트 개요

    1) Thymeleaf 이용한 html 활용

    2) erd상의 table pk-fk 설정 조건 파악 (@OneToMany, @ManyToOne 등)

    3) 각 파트(Cart,Course,Enrollment,Student 이하 파트)별 Service 구현

    4) JPQL(또는 QueryDSL) Custom Query 적용한 service 구현을 위해 interface, method 설계

    
2. 프로젝트 진행

    1) 전체 프로젝트 구조 숙지
   
       (1) config       : 전역 설정, 환경 설정 관리 등(MvcConfig, SecurityConfig etc.)
       (2) controller   : PageController (model에 데이터 객체 넣고 반환은 .html로 가는 형식)
       (3) core         : enum, baseEntity 등
       (4) models       : 각 파트 별 domain, repository, dto, service 구현

    2) dto, service, repository 구현

    3) PageController 상의 html thymeleaf로 구현

    4) 추후 ApiController(RestController) 방식 구현을 위한 설계
        
        a) 기존의 model에 들어가는 데이터 객체에 대한 인지

        b) 해당 데이터를 ApiController로 전환해서 dto 또는 JSON으로 반환되는 형식에 대한 방식 공부

        c) 현재 project 구조에서 PageController와 ApiController가 독립적으로 실행될 수 있는 구조에 대한 이해