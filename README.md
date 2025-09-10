# 풋살 매칭 플랫폼 ⚽





## 프로젝트 개요

풋살을 즐기는 이용자들이 팀 단위로 매칭을 신청하고 경기 일정을 관리할 수 있는 플랫폼입니다.

1. 매칭 일정 생성 / 조회 / 취소
2. 팀원 관리 및 상대팀 조회
3. 결제 상태 확인 및 변경
4. 경기 후 리뷰 & 점수 입력
5. 불공정 플레이 신고 기능


## 개발 일정
- 총 개발 기간: 24.06.19 ~ 24.07.23

  
![일정표](https://github.com/mi-gang/football-matching-platform/blob/master/%EC%9D%BC%EC%A0%95%ED%91%9C.png)


## 팀원 및 역할
- 전성훈
  - 메인, 로그인, 화원가입 FE 구현
  - 매칭, 구장 매니저 BE 구현
- 유명주
  - 시스템 관리자, 구장 매니저, 팀 FE 구현
  - 팀, 구장 매니저 BE 구현
- 차미강
  - 일정표, 마이페이지 FE 구현
  - 일정표 BE 구현
- 최주호
  - 매칭, 빠른 신청 FE 구현
  - 메인, 빠른 신청, 시스템 관리자 BE 구현
- 강건우
  - 팀 FE 구현
  - 마이페이지 BE 구현


## 소프트웨어 아키텍처


![소프트웨어 아키텍처](https://github.com/mi-gang/football-matching-platform/blob/master/%EC%86%8C%ED%94%84%ED%8A%B8%EC%9B%A8%EC%96%B4%20%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98.png)

## 사용 기술 스택

**기획 및 협업**
- Notion
- 구글 스프레드시트
- Figma
- ERD Cloud
- Git

**테스트**
- Junit5 (+ Assertj 3.25)
- Postman

**구현**

**클라이언트**
- ThymeLeaf
- Node.js - React - Axios

**서버**
- Maven
- Spring Boot 3.3.0
- Spring Boot Security 6.3.0 (+JWT)
- MyBatis
- JPA

**DB**
- MariaDB

**배포**
- Amazon EC2
- Amazon S3
