일정 캘린더
-
- 로그인을 활용하여 일정공유 및 일정 등록이 가능한 프로그램입니다

프론트 경로 /src/main/frontend  
-
필요 라이브러리 설치 
- antd 5.21.6 버전 설치
- dayjs 1.11.13 버전 설치
- axios 1.7.7 버전 설치
- react router 설치

웹서버 기동시 3000번 포트로 기동(cors 설정을 3000번 포트에서 입력된 경우만 통과)
```
npm start 
```

백엔드 경로 src/main/java/com/reactProject/exam/backend/  
-
구동시 필요한 테이블 및 스키마 구문 
```sql
CREATE DATABASE react_project;

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
