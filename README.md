# 멋사스네스(MutsaSNS)

## 서비스 소개

### 1️⃣ 회원 인증·인가

- 모든 회원은 회원가입을 통해 회원이 됩니다.
- 로그인을 하지 않으면 SNS 기능 중 피드를 보는 기능만 가능합니다.
- 로그인한 회원은 글쓰기, 수정, 댓글, 좋아요, 알림 기능이 가능합니다.

### 2️⃣ 글쓰기

- 포스트를 쓰려면 회원가입 후 로그인(Token받기)을 해야 합니다.
- 포스트의 길이는 총 300자 이상을 넘을 수 없습니다.
- 포스트의 한 페이지는 20개씩 보이고 총 몇 개의 페이지인지 표시가 됩니다.
- 로그인 하지 않아도 글 목록을 조회 할 수 있습니다.
- 수정 기능은 글을 쓴 회원만이 권한을 가집니다.
- 포스트의 삭제 기능은 글을 쓴 회원만이 권한을 가집니다.

### 3️⃣ 마이피드

- 로그인 한 회원은 자신이 작성한 글 목록을 볼 수 있습니다.

### 4️⃣ 댓글

- 댓글은 회원만이 권한을 가집니다.
- 글의 길이는 총 100자 이상을 넘을 수 없습니다.
- 회원은 다수의 댓글을 달 수 있습니다.

### 5️⃣ 좋아요

- 좋아요는 회원만 권한을 가집니다.
- 좋아요 기능은 취소가 가능합니다.

### 6️⃣ 알림

- 알림은 회원이 자신이 쓴 글에 대해 다른회원의 댓글을 올리거나 좋아요시 받는 기능입니다.
- 알림 목록에서 자신이 쓴 글에 달린 댓글과 좋아요를 확인할 수 있습니다.

---

## 개발환경

- 에디터 : Intellij Ultimate
- 개발 툴 : SpringBoot 2.7.5
- 자바 : JAVA 11
- 빌드 : Gradle 6.8
- 서버 : AWS EC2
- 배포 : Docker
- 데이터베이스 : MySql 8.0
- 필수 라이브러리 : SpringBoot Web, MySQL, Spring Data JPA, Lombok, Spring Security

---
## 미션 요구사항 분석 & 체크리스트
미션 요구사항 분석

**첫번째 미션**
- AWS EC2 사용해서 Docker로 배포
- CI (Gitlab) / CD (Crontab) 구성
- REST API 명세를 문서화하기 위해 Swagger 사용
- 회원 인증·인가 - 회원가입 POST /api/v1/users/join
- 회원 인증·인가 - 로그인 POST /api/v1/users/login
- 포스트 - 포스트 리스트 GET /api/v1/posts
- 포스트 - 포스트 상세 GET /api/v1/posts/{postId}
- 포스트 - 포스트 등록 POST /api/v1/posts
- 포스트 - 포스트 수정 PUT /api/v1/{postId}
- 포스트 - 포스트 삭제 DELETE /api/v1/{postId}
- ErrorCode Enum으로 적용
- User (UserController/UserService) Test Code
- Post (PostController/PostService) Test Code

**두번째 미션**
- 댓글 - 댓글 목록 조회 GET /api/v1/posts/{postId}/comments?page=0
- 댓글 - 댓글 작성 POST /api/v1/posts/{postId}/comments
- 댓글 - 댓글 수정 PUT /api/v1/posts/{postId}/comments/{id}
- 댓글 - 댓글 삭제 DELETE /api/v1/posts/{postId}/comments/{id}
- 좋아요 - 좋아요 누르기 및 취소 POST /api/v1/posts/{postId}/likes
- 좋아요 - 좋아요 개수 조회 GET /api/v1/posts/{postId}/likes
- 마이피드 - 마이피드 조회 GET /api/v1/posts/my
- 알람 - 알림 조회 GET /api/v1/alarms
- Myfeed (PostController/PostService) Test Code
- Comment (CommentController/CommentService) Test Code
- Like (LikeController/LikeService) Test Code
- Alarm (AlarmController/AlarmService) Test Code

### 체크리스트

**첫번째 미션**
- [x] AWS EC2 인스턴스 생성
- [x] Docker로 배포
- [x] Gitlab CI .yml 파일 만들기
- [x] Swagger 적용
- [x] deploy shell script 만들어서 crontab 적용
- [x] 회원 인증·인가 기능
- [x] 포스트 기능
- [x] ErrorCode Enum으로 생성
- [x] ExceptionManager 만들기
- [ ] User Test Code
- [ ] Post Test Code

**두번째 미션**
- [x] 댓글 - 댓글 목록 조회 GET /api/v1/posts/{postId}/comments?page=0
- [x] 댓글 - 댓글 작성 POST /api/v1/posts/{postId}/comments
- [x] 댓글 - 댓글 수정 PUT /api/v1/posts/{postId}/comments/{id}
- [x] 댓글 - 댓글 삭제 DELETE /api/v1/posts/{postId}/comments/{id}
- [x] 좋아요 - 좋아요 누르기 및 취소 POST /api/v1/posts/{postId}/likes
- [x] 좋아요 - 좋아요 개수 조회 GET /api/v1/posts/{postId}/likes
- [x] 마이피드 - 마이피드 조회 GET /api/v1/posts/my
- [x] 알람 - 알림 조회 GET /api/v1/alarms
- [ ] Myfeed (PostController/PostService) Test Code
- [ ] Comment (CommentController/CommentService) Test Code
- [ ] Like (LikeController/LikeService) Test Code
- [ ] Alarm (AlarmController/AlarmService) Test Code
---
### 첫번째 미션 요약

- **AWS EC2 인스턴스 생성 및 Docker로 배포**

  t2.micro 인스턴스를 생성해서 인스턴스에 Docker 설치해서 Spring Boot 프로젝트를 Docker로 배포했다.


- [**CI (Gitlab) / CD (Crontab) 구성**](https://itcouldbe-v.tistory.com/9)

  [Dockerfile](https://gitlab.com/yejinstar/finalproject_gimyejin_team12/-/blob/main/Dockerfile) 과 [.gitlab-ci.yml](https://gitlab.com/yejinstar/finalproject_gimyejin_team12/-/blob/main/.gitlab-ci.yml) 파일을 생성해서 Gitlab에서 main으로 push 할 때마다 build하게 하는 CI를 구성한다. crontab으로 인스턴스에서 새로운 container registry가 생성될 때마다 새롭게 container를 띄울 수 있도록 deploy shell scrpit를 실행시켜서 CI / CD 자동화를 적용한다.


- **Swagger 적용**

  Swagger 적용해서 API 명세 관리와 직접 통신을 시도해서 요청을 보내고 결과값을 받아보며 Test 했다.

  http://ec2-13-209-70-189.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/


- **ERD**

    <img src="https://user-images.githubusercontent.com/70505584/211463227-79e55418-3586-4649-bd0d-71f4315ef8ab.png" width="300" height="400">


- **Join**

  회원가입 기능
  UserJoinRequest로 회원 이름과 비밀번호를 담아 요청을 보내면 BCryptPasswordEncoder를 사용해서 비밀번호를 암호화 하여 DB에 넣어주는 과정으로 회원가입을 구성한다. 
  UserJoinResponse로 회원가입 결과를 return 받는다.
  ![image](https://user-images.githubusercontent.com/70505584/211472912-40243db2-fc6c-4b7d-a500-8446d1490431.png)


- **Login**
  로그인 기능
  ![image](https://user-images.githubusercontent.com/70505584/211475063-5f16be2f-dc09-4e09-9dea-34309fc1876f.png)


- **Post 조회**


- **Post 작성**
  ![image](https://user-images.githubusercontent.com/70505584/211479070-d79fb38f-5a43-4957-9af1-e8ff43ef72b8.png)


- **Post 수정**
  ![image](https://user-images.githubusercontent.com/70505584/211478961-25a3d7fb-6e84-4aa7-a502-85a9fef5280e.png)

- **Post 삭제**



**특이사항**

- TDD가 습관화 되어있지 않아서 Test 코드 작성에서 많은 혼란이 있었다. WebMvcTest를 더 깊게 공부해보고 Test Code 작성해볼것이다.
- 코드 리팩토링 할 때, Factory Method Pattern으로 코드 리팩토링해서 객체 생성하는 코드를 분리해서 유지보수에 편하게 만들어볼 것이다.
- 이번 미션하면서 읽어보고 공부했던 Security, Lombok, Page 내용 정리하기
- ERD와 UML 다이어그램 그려보기

---
### 두번째 미션 요약
- **Comment 목록 조회**
  ![image](https://user-images.githubusercontent.com/70505584/211477198-cca2e687-231e-4cd6-a18a-13ccc77dd0d1.png)


- **Comment 작성**
  
  ![image](https://user-images.githubusercontent.com/70505584/211476427-78d698bc-b22a-4140-a71d-ec31d498ee1d.png)


- **Comment 수정**
  
  ![image](https://user-images.githubusercontent.com/70505584/211476827-05ecea24-5a25-4e07-9192-49649bd51c29.png)


- **Comment 삭제**
  ![image](https://user-images.githubusercontent.com/70505584/211477327-8a50c81f-7f1c-452f-8d5d-2069e0f38b7f.png)


- **Like 등록 및 취소**
  ![image](https://user-images.githubusercontent.com/70505584/211478138-ad17d8d8-a75c-40bf-8651-5789c1f8735b.png)


- **Like 등록 및 취소**
  ![image](https://user-images.githubusercontent.com/70505584/211477757-fe572be6-9270-4024-b488-acda30bbe3e8.png)
  ![image](https://user-images.githubusercontent.com/70505584/211477808-c05ec82f-77f0-434d-bfb9-19a00caf86be.png)


- **Myfeed 조회**
  ![image](https://user-images.githubusercontent.com/70505584/211479210-e0cb1795-0e44-4c42-9b4a-7c289ea0257c.png)


- **Alarm 조회**
  ![image](https://user-images.githubusercontent.com/70505584/211479383-b1a2d282-054a-4656-83e1-db6915099e66.png)


**접근방법**

- Comment 기능은 Post 기능과 유사하고 연관관계 하나를 더 추가해주면 됐다. Post에 달리는 Comment이기 때문에 서로 관계를 맺어주었다.
- Like 기능에서는 특정 유저가 특정 게시글에 좋아요를 한번만 누를 수 있는 기능이었는데, Like 재등록시 취소가 되도록 구현했다.
- Soft Delete 처리를 위해서 deleted_at 이라는 컬럼을 추가해서 삭제 시간의 유무로 논리 삭제 처리 되도록했다. 
- 게시글, comment 삭제에서 Soft Delete 처리를 하기 위해서 각 Entity에 `@Where`로 deleted_at에 값의 유무를 확인하는 쿼리를 붙여주는 것으로 삭제 확인하는 열에 값이 없는 것만 가져오도록 처리했다.
  Post와 Comment는 내용이 들어가기 떄문에 삭제되고 재등록 할 때의 값이 DB에 등록되는 내용마다 저장하는 것이 맞다. 하지만 Like는 좋아요의 유/무 만 기록되고 인당 1개의 좋아요만 가능하기 때문에 
  좋아요를 취소했다가 다시 등록하는 과정에서 매번 DB에 행을 추가하지 않고 deleted_at 칼럼을 null로 만들어주면 다시 좋아요 처리가 된다.
  그래서 위의 Post, Comment와는 다르게 LikeService에서 `@Query`를 사용해서 직접 쿼리를 작성했다.
- 회원제 SNS를 만들고 싶어서 인증과 접속 권한 범위를 SecurityFilterChain으로 설정했다.  

**특이사항**

- Soft Delete를 적용하면서 이미 구현해 놓은 코드를 리팩토링하는 시간을 가졌다. 그리고 `@Query` 어노테이션으로 쿼리를 직접 작성해보면서 
  JPQL과 SQL 작성하는 방법에 대해 공부해보는 시간을 가졌다.
- 미션 내용에서 Like 두번 누를 때 오류를 발생시켜야하지만 취소하는 기능으로 바꿔서 진행했다.
  Comment 조회도 비회원이 가능하게 미션이 구성되어 있었지만, 회원제 게시판으로 만들고 싶어서 댓글 공개범위를 회원만 가능으로 설정했다.
- TDD 와 JUnit5를 공부해서 Test Code를 완성할 것이다.
- Spring Security 인증 과정과 FilterChain 예외처리, Security 필터 단에서의 예외처리를 공부하고 정리해볼것이다.
- 전역으로 예외처리 방법을 적용해 볼 것이다.
- 코드 리팩토링 할 때, Factory Method Pattern으로 코드 리팩토링해서 객체 생성하는 코드를 분리해서 유지보수에 편하게 만들어볼 것이다.
