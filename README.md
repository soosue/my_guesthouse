# My GuestHouse refactoring project

## Version
- Java : 17
- Gradle : 7.4.1
- Oracle : 11g xe

## 구동방법
- 준비해야 할 것은 3가지이다. 
  1. 이미지 파일들을 준비한다.
  2. 데이터베이스를 준비한다.
  3. 데이터베이스에 데이터를 임포트한다.


방법은 아래와 같다.
1. import 디렉토리 안의 pde, pdn, profile.zip 압축을 풀어 `ResourceConfiguration`클래스의 resource 경로에 맞게 위치시킨다.
      - window 가 아닌 경우엔 `ResourceConfiguration` 경로를 적절하게 수정한다.
2. oracle DB를 `DbConfiguration.dataSource()`를 참조하여 준비한다.
3. DB 데이터는 import 디렉토리 안의 임포트.sql 을 import 한다.
   - 일부 명령어(`REM INSERTING into PROJECT.EXWISHLIST`, `SET DEFINE OFF`) 때문에 sql developer 를 이용하는 것을 추천한다.
4. `GuestHouseApplication.main()`을 실행한다.
