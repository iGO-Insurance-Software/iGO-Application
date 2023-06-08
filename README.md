# iGO
2005년도 '신동아화재' 보험사 시스템 구축 제안서(RFP) 내용에 기반한 보험사 소프트웨어 설계 &amp; 구축 프로젝트

1. mysql 실행
2. mysql -u root -p 로 접속
3. CREATE DATABASE insurance; 로 데이터베이스 생성
4. 프로젝트 내 Dao 패키지의 dao.java 내 password 필드의 값을 자신의 mysql 접속 비밀번호로 설정.
5. 프로젝트 실행
6. 7초 내에 'mngmode' 입력 후 1번을 눌러 테이블 & 더미 데이터 세팅
7. 이후 진행
