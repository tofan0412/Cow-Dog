# 🐂🐕COW & DOG

![LogoBackgroundInvisible](README.assets/LogoBackgroundInvisible.png)

<div>
<p align="center">
  <br/><b>소개팅 서비스</b>
  <p align="center">
 <img src="https://img.shields.io/badge/JavaScript-ES6+-green?logo=javascript">
 <img src="https://img.shields.io/badge/Vue.js-v3.0.0-green?logo=vue.js">
 <img src="https://img.shields.io/badge/Vuetify-v3-green?logo=Vuex">
 <img src="https://img.shields.io/badge/Java-v1.8-blue?logo=java">
 <img src="https://img.shields.io/badge/spring boot-v2.5.2-blue?logo=spring">
 <img src="https://img.shields.io/badge/AWS%20RDS----blue?logo=amazonaws">
 <img src="https://img.shields.io/badge/MySQL-v8.0-blue?logo=mysql">
  </p>
<br/>  
<br/> 
</p>    
</div>

> 서비스명: CowDog
> 팀명: 5xy
> 개발 기간: 2021.07.12 ~ 2021.08.20 (약 7주) 



## 📑 문서 

> [기획문서](https://www.notion.so/1-1e3462cc2d1247918beef6ec804b1df5)



## 📑 활용 API(임시)

**Face Filter**

jeeliz(JavaScript) https://github.com/jeeliz/jeelizFaceFilter

snow web filter(JavaScript) https://github.com/doublejbs/snow-web-filter

kairess(Python) https://github.com/kairess/face_detector

kairess 가이드 영상 https://www.youtube.com/watch?v=tpWVyJqehG4

스냅챗필터(Python) https://github.com/oflynned/Snapchat-Filter

스냅챗 문서 url https://syzible.com/snapchat-filters.pdf

ASCII virtual webcam(Python) https://github.com/diegovazquez/ASCIILinuxVirtualWebCam

banuba(상업용?) https://www.banuba.com/facear-sdk/face-filters



**Voice Filter**

https://github.com/funcwj/voice-filter





## 👨 팀원 역할   
| 이름   | 역할 | 내용                        |
| ------ | ---- | --------------------------- |
| 김정운 | 팀장 | 백엔드, 기획 담당자 |
| 오웅식 | 팀원 | 백엔드, 테크리더       |
| 배현우 | 팀원 | 백엔드, QA |
| 조웅현 | 팀원 | 프론트엔드, QA, 프론트엔드 테크리더 |
| 유성호 | 팀원 | 프론트엔드, 기획 담당자 |



 


## 📑 Gantt Chart
   ```mermaid
   gantt
   dateFormat  YYYY-MM-DD
   title       5xy 공통 프로젝트(cowdog)
   excludes    weekends
   
   section 프로젝트 기획
   주제 선정       :done, sub1, 2021-07-12, 5d
   Jira, Git 생성 :done, sub2, 2021-07-19, 2h
   명세서 작성 :active, sub3, 2021-07-15, 2021-07-20
   Jira issue 생성: active, sub4, 2021-07-20, 2d
   
   section 설계
   DB 설계 : sub1, 2021-07-23, 2021-07-24
   와이어 프레임 설계 : 2021-07-20, 2021-07-22
   시퀀스 설계: 2021-07-20, 2021-07-22
   
   section 회원 관련 구현(김정운)
   회원 가입 : sub1, 2021-07-24, 2021-07-27
   마이페이지 : sub2, 2021-07-24, 2021-07-27
   회원 탈퇴 :sub3, 2021-07-25, 2021-07-26
   회원정보 수정 :sub4, 2021-07-25, 2021-07-26
   로그인 :sub5, 2021-07-27, 2021-07-30
   로그아웃 :sub6, 2021-07-25, 2021-07-26
   비밀번호 찾기 :sub7, 2021-07-25, 2021-07-26
   
   section 게시판(조웅현)
   게시판 글 등록:sub1, 2021-07-26, 2021-07-27
   게시판 글 삭제:sub2, 2021-07-27, 2021-07-28
   게시판 글 수정:sub3, 2021-07-28, 2021-07-29
   
   section 관리자 기능(유성호)
   공지사항 :sub1, 2021-07-25, 2021-07-26
   신고:sub2, 2021-07-25, 2021-07-27
   
   section 게임(유성호)
   A or B 게임 :sub1, 2021-08-01, 2021-08-05
   캐치마인드 :sub2, 2021-08-05, 2021-08-17
   
   section webRTC(오웅식, 배현우)
   사전 공부 및 예제 실습 : sub1, 2021-07-24, 2021-07-25
   RTC 통신 구조 설계 :sub2, 2021-07-26, 2021-07-27
   RTC 1:1 통신 구현 : sub3, 2021-07-28, 2021-07-30
   얼굴 필터링 기능 :sub4, 2021-08-17, 2021-08-18
   목소리 변조 기능 :sub5, 2021-08-17, 2021-08-18
   
   section 채팅(오웅식, 배현우, 김정운)
   채팅기능 :sub1, 2021-08-01, 2021-08-05
   
   section 부가기능(오,배,김)
   좋아요 팔로우 :sub1, 2021-08-06, 2021-08-08
   알림 기능 :sub2, 2021-08-09, 2021-08-12
   DM :sub3, 2021-08-13, 2021-08-16
   
   section 매칭(조웅현, 오웅식)
   오늘의 추천:sub1, 2021-07-31, 2021-08-20
   지역 추천:sub2, 2021-07-31, 2021-08-10
   맞춤 추천:sub3, 2021-08-11, 2021-08-15
   
   section UCC
   UCC 제작: sub1, 2021-08-19, 2021-08-20
   ```




## 📑 서비스 개요      
코로나 시대 2030을 위한 소개팅 서비스







## 📑 주요 기능     
수정사항



## 📑 실행방법 
### ✔ 포트번호    

```
프론트 : 3000 -> 8000
백 : 8080
DB : 3306
Jenkins : 9090
```




### ✔ 실행순서

미정



## 📑 규칙

### ✔ 브랜드 컬러 & 폰트

**브랜드 컬러:** White, <span style="color:#FF4E7E">#FF4E7E</span>, <span style="color:#323545">#323545</span> (핑크와 다크 그레이)

**폰트:** 서울남산체



### ✔ Branch 규칙  
```bash
develop - feature/기능명/fe or be       
```



### ✔ Code Style
| FrontEnd   | BackEnd |
| ------ | ---- |
| 1. 폴더명: 첫글자 대문자→ ex) Feed/Add.vue<br />2. 파일명: 첫글자 대문자  → ex) Add.vue <br/>3. 경로명: 소문자 → ex) /add | 1. 클래스명:  첫글자 대문자 + camel case <br/> ex) MainController.java <br/><br/> 2. 함수, 변수: 첫글자 소문자 + camel case <br/> ex) public void setUserName(); |
|- ☑ 프론트앤드 규칙||
    - 플러그인 : Vuetify 사용
    - css는 import해서 사용 -> style.css에서 공통속성 사용
    - 공통파일 수정 시 팀채널에 공유하기 
    - http파일 import해서 사용하기 


- ☑ 백앤드 명명규칙 
    - [참고](https://velog.io/@aidenshin/Java-%EC%9E%90%EB%B0%94-%EC%BD%94%EB%94%A9-%EA%B7%9C%EC%B9%99-Java-Code-Conventions#%EB%AA%85%EB%AA%85naming-%EA%B7%9C%EC%B9%99)  



- if문
    - 한줄 일 때, Block 처리하기
    - else if / else /중괄호는 조건문 바로 옆에 붙이기

    ```java
    if(condition){
     statement;
    } else if(condition2){
     statement2;
    } else{
     statement3;
    }
    ```

- for
  
    - 단순 반복문은 iterator를 i,j,k,...,z순으로 명명하기
- 주석 상대방이 이해할 수 있도록 달기
    - /**/ 설명 여러줄 필요할 때 코드 위에 작성
    - // 간단한 주석 코드 옆에 작성



### ✔ commit 규칙    

📌 하나의 기능 완성되면 한번에 커밋

```
git commit -m "{지라이슈번호} [작업분류] : {FE or BE} - 작업커밋내용"
git commit -m "S04P22B107-56 [feat] : BE_0415 - AWS RDS, S3 서버 및 기본환경 세팅"

**프론트** : "{지라이슈번호 하나}_[작업분류] : FE_날짜:완성한 기능(작업내용)" 
		ex) S04P22B107-56_ [feat] : FE_0118:피드작성 컴포넌트 추가/수정/삭제

**백** : "{지라이슈번호 하나}_[작업분류] : BE_날짜: 완성한 기능(작업내용)" 
		ex) S04P22B107-56__ [feat] : BE_0118: 피드작성 컴포넌트 추가/수정/삭제

**리드미** : "README_날짜: 수정한 부분" 
		ex) S04P22B107-56_ [feat] : README_0118: 와이어프레임 수정

```
```
[ 작업 분류 ]
- feat : 새로운 기능 추가
- fix : 버그 수정
- docs : 문서 수정
- style : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
- refactor : 코드 리펙토링
- test : 테스트 코드, 리펙토링 테스트 코드 추가
- chore : 빌드 업무 수정, 패키지 매니저 수정   
```



## 📑산출물  

> 1. ER 다이어그램     
> 2. 와이어프레임  

### ✔ ER 다이어그램      



### ✔ 와이어프레임    
> [피그마 바로가기](https://www.figma.com/file/DgYRBtDjlOecYPiEZOxTAi/Untitled?node-id=0%3A1)    



### 2021.07.23

- vue router 추가

