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

### 3️⃣ 피드

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

## 첫번째 미션

### 미션 요구사항 분석 & 체크리스트

---

**미션 요구사항 분석**

- REST API 명세를 문서화하기 위해 Swagger 사용
- AWS EC2 사용해서 Docker로 배포
- CI (Gitlab) / CD (Crontab) 구성
- 회원 인증·인가 - 회원가입 POST /api/v1/users/join
- 회원 인증·인가 - 로그인 POST /api/v1/users/login
- 포스트 - 포스트 리스트 GET /api/v1/posts
- 포스트 - 포스트 상세 GET /api/v1/posts/{postId}
- 포스트 - 포스트 등록 POST /api/v1/posts
- 포스트 - 포스트 수정 PUT /api/v1/{postId}
- 포스트 - 포스트 삭제 DELETE /api/v1/{postId}
- ErrorCode Enum으로 적용
- User (Controller/Service) Test Code
- Post (Controller/Service) Test Code

**체크리스트**

- [x] Swagger 적용
- [x] AWS EC2 인스턴스 생성
- [x] Docker로 배포
- [x] Gitlab CI .yml 파일 만들기
- [x] deploy shell script 만들어서 crontab 적용
- [x] 회원 인증·인가 기능
- [x] 포스트 기능
- [x] ErrorCode Enum으로 생성
- [x] ExceptionManager 만들기
- [ ] User Test Code
- [ ] Post Test Code

### 첫번째 미션 요약

---

**접근방법**

- Swagger 적용

    ```java
    implementation "io.springfox:springfox-boot-starter:3.0.0"
    implementation "io.springfox:springfox-swagger-ui:3.0.0"
    ```

    ```java
    @Configuration
    public class SwaggerConfig {
        @Bean
        public Docket api(){
            return new Docket(DocumentationType.OAS_30)
                    .select()
                    .apis(RequestHandlerSelectors.any())
                    .paths(PathSelectors.any())
                    .build();
        }
    }
    ```
  Swagger 적용해서 API 명세 관리와 직접 통신을 시도해서 요청을 보내고 결과값을 받아보며 Test 했다.


- AWS EC2 인스턴스 생성 및 Docker로 배포

  t2.micro 인스턴스를 생성해서 인스턴스에 Docker 설치해서 Spring Boot 프로젝트를 Docker로 배포했다.


- [CI (Gitlab) / CD (Crontab) 구성](https://itcouldbe-v.tistory.com/9)

  [Dockerfile](https://gitlab.com/yejinstar/finalproject_gimyejin_team12/-/blob/main/Dockerfile) 과 [.gitlab-ci.yml](https://gitlab.com/yejinstar/finalproject_gimyejin_team12/-/blob/main/.gitlab-ci.yml) 파일을 생성해서 Gitlab에서 main으로 push 할 때마다 build하게 하는 CI를 구성한다. crontab으로 인스턴스에서 새로운 container registry가 생성될 때마다 새롭게 container를 띄울 수 있도록 deploy shell scrpit를 실행시켜서 CI / CD 자동화를 적용한다.

  CI / CD 과정을 자동화하니까 기능 추가마다 배포과정을 직접 수행하지 않아도 되어서 기능 개발에 더 시간을 쓸 수 있었다.


- 회원 인증·인가 기능 - 회원가입

  POST /api/v1/users/join 엔드포인트





**특이사항**

- Factory Method Pattern으로 코드 리팩토링
- WebMvcTest 공부해보고 Test Code 작성