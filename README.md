# iGO
2005년도 '신동아화재' 보험사 시스템 구축 제안서(RFP) 내용에 기반한 보험사 소프트웨어 설계 &amp; 구축 프로젝트

1. mysql 실행
2. mysql -u root -p 로 접속
3. CREATE DATABASE insurance; 로 데이터베이스 생성
4. 프로젝트 내 Dao 패키지의 dao.java -> password 필드의 값을 자신의 mysql 접속 비밀번호로 설정.
5. IDE(intelliJ 권장) 실행, 프로젝트 open, lib 폴더의 Connector J 우클릭 - Add as Library로 라이브러리 추가
6. 프로젝트 실행
7. 7초 내에 'mngmode' 입력 후 1번을 눌러 테이블 & 더미 데이터 세팅
8. 이후 진행
9. 테스트용 로그인 id 리스트
  - 직원 <br>
  상품개발팀 : pd2025 <br>
  컴플라이언스팀 : ct2026 <br>
  마케팅팀 : me2023 <br>
  영업팀 : se2023 , se2024 <br>
  UW팀 : uw01 <br>
  계약관리팀 : cm2023 <br>
  사고접수팀 : re2023, re2024 <br>
  현장조사팀 : ie2023, ie2024 <br>
  보상팀 : ce2023 <br>
  - 고객 <br>
  일반고객 : cm2023 <br>
  피보험자 : ics2023, ics2024 <br>
