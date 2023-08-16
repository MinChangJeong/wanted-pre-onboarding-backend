## 원티드 백엔드 선발과제 

----

1. [지원자의 성명](#1.-지원자의-성명)
2. [애플리케이션의 실행방법 with Docker](#2.애플리케이션의-실행방법-with-Docker)
3. [데이터베이스 테이블 구조](#3.-데이터베이스-테이블-구조)
4. [API 데모](#4.-API-데모)
5. [구현 방법 및 이유](#5.-구현-방법-및-이유)
6. [API-명세서 with Spring REST Docs](#6.-API-명세서-with-Spring-REST-Docs)

----

#### 1. 지원자의 성명

안녕하세요 백엔드 지원자 정민창입니다. 

----

#### 2. 애프리케이션 실행 방법 with Docker

2-1. git repository clone

```bash
git clone https://github.com/MinChangJeong/wanted-pre-onboarding-backend.git
```

2-2. gradle build

```bash
./gradlew build
```

2-3. docker compose up(Daemon)

```bash
docker compose up --build -d
```

----

#### 3. 데이터베이스 테이블 구조

<img width="261" alt="스크린샷 2023-08-16 오후 12 00 19" src="https://github.com/MinChangJeong/wanted-pre-onboarding-backend/assets/65451455/11abf32a-4790-4d3b-a890-bd7d21e8a239">

----

#### 4. API 데모

https://drive.google.com/drive/u/0/folders/0AIKY_zsQTW4nUk9PVA?q=parent:0AIKY_zsQTW4nUk9PVA

----

#### 5. 구현 방법 및 이유

* 과제 1. 사용자 회원가입 엔드포인트
   * 회원가입을 위한 이메일은 @Email로, 패스워드는 @MinLength라는 커스텀 어노테이션을 만들어 유효성 검사를 진행
   * 비밀번호는 해시 암호화를 사용하여 단방향 암호화하여 저장, 단방향 암호화는 복호화가 불가능하기에 로그인시 전달받은 패스워드를 해시 암호화하여 값이 일치하는지 확인하도록 구현

* 과제 2. 사용자 로그인 엔드포인트
  * JwtProvider를 사용해 JWT 생성, claims에 memberId와 email값을 저장함.
  * JWT는 Arugment Resolver로 헤더에서 키값인 'Authoriztion'이 있는지 확인하고 유효한 토큰인지 검사함. 회원가입, 게시글 목록 및 상세 조회를 제외한 엔드포인트는 JWT 토큰을 검사함.
  * JWT검사는 @JwtAuthorization 커스턴 어노테이션을 만들었음. @JwtAuthorization는 AuthenticationUser 객체에 붙어서 controller의 파라미터로 전달함. default는 true로 설정되어 @JwtAuthorization가 붙은 엔트포인트는 JWT 토큰을 검사하도록 설계함.
 
* 과제 3. 새로운 게시글을 생성하는 엔드포인트
  * 로그인을 한 사용자만 게시글을 생성할 수 있음.
  * 게시글을 생성하기 위해 게시글 제목과 내용을 값으로 받음.

* 과제 4. 게시글 목록을 조회하는 엔드포인트
  * Paginnation을 구현함. (default는 page=0, size=10)
 
* 과제 5. 특정 게시글을 조회하는 엔드포인트
  * 게시글의 ID를 받아 해당 게시글을 조회.
 
* 과제 6. 게시글을 수정하는 엔드포인트
  * 로그인을 한 사용자만 게시글을 수정할 수 있음.(성공적으로 로그인을하면 AuthenticationUser에 memberId값이 할당됨)
  * 게시글을 작성자만 게시글을 수정할 수 있도록 검사.
 
* 과제 7. 특정 게시글을 삭제하는 엔드포인트
  * 로그인을 한 사용자만 게시글을 삭제할 수 있음.(성공적으로 로그인을하면 AuthenticationUser에 memberId값이 할당됨)
  * 게시글을 작성자만 게시글을 삭제할 수 있도록 검사.

----

#### 6. API 명세서 with Spring REST Docs

* [API 명세서](https://minchangjeong.github.io/project/)






