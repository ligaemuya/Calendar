일정 캘린더
-
- 로그인을 활용하여 일정공유 및 일정 등록이 가능한 프로그램입니다

프론트 경로 /src/main/frontend  
-
터미널에서 프론트 경로로 이동후 
```
npm install
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

application.properties db 설정
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/react_project
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

