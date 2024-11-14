개발한 앱에 대한 설명
-
- 로그인을 활용하여 일정공유 및 일정 등록이 가능한 프로그램입니다


실행방법
-

아래 환결설정진행은 인텔리제이IDE환경 기준으로 작성하였습니다
소스를 풀 받았다는 가정하에 작성

프론트 경로 /src/main/frontend  

터미널에서 프론트 경로로 이동후 
```
npm install
npm start 
```
3000번 포트에서 기동

application.properties db 계정정보
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/react_project
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

구동시 필요한  스키마 및 테이블 구문 
```sql
CREATE SCHEMA react_project;

CREATE TABLE users (
    user_uuid CHAR(36) NOT NULL,           
    user_id VARCHAR(255) NOT NULL UNIQUE,  
    username VARCHAR(255) NOT NULL,        
    password VARCHAR(255) NOT NULL,        
    created_at DATETIME NOT NULL,          
    updated_at DATETIME NOT NULL,          

    PRIMARY KEY (user_uuid)                
);

CREATE TABLE calendar (
    id VARCHAR(255) NOT NULL,                  
    title VARCHAR(255) NOT NULL,               
    description TEXT,                          
    start_time TIMESTAMP NOT NULL,             
    end_time TIMESTAMP NOT NULL,               
    user_id VARCHAR(255) NOT NULL,             
    share BOOLEAN NOT NULL,                    

    PRIMARY KEY (id)                           
);
```
테이블 생성후 

인텔리제이  
설정 - Gradle - 빌드 및 실행 - IntelliJ IDE 선택  
설정 - Gradle - 테스트 실행항목 - IntelliJ IDE 선택  
설정 - Gradle - GradleJVM - JAVA17선택

백엔드 경로 src/main/java/com/reactProject/exam/backend/
ExamApplication 기동  
8080번 포트에서 기동해야함

주력으로 사용한 컴포넌트에대한 설명 및 사용 이유 기입
-
- 프론트

Ant Design : 과제의 조건이기도 하였지만 해당 프로젝트에서 핵심 컴포넌트라고 생각되어 내용을 적었습니다.
axios: 프론트엔드와 백엔드 간의 HTTP 통신을 쉽게 처리하기 위해 사용했습니다.

- 백엔드

JPA : 쿼리작성에 시간을 줄이기위해 사용하였습니다

API 명세 
- 
http://localhost:8080/swagger-ui/index.html#/

기본 CRUD 업무 이외에 추가적인 업무 Api 필수
-
캘린더에서 일정공유기능을 확인하기위해 
로그인 API기능을 추가해보았습니다 
해당 공유기능을 확인하기위해선 2개의 계정을 생성 후  
일정 추가시 공유를 체크후 일정생성진행  
다른계정으로 로그인하여 다른계정의 일정이 보이는지 확인하면 됩니다  

감사합니다 

