# My GuestHouse refactoring project

## Version
- Java : 17
- Gradle : 7.4.1
- Oracle : 11g xe

## 구동방법
- 준비해야 할 것은 3가지이다. 
  1. 데이터베이스를 준비한다.
  2. 데이터베이스에 데이터를 임포트한다.


방법은 아래와 같다.
1. oracle DB를 `DbConfiguration.dataSource()`를 참조하여 준비한다.
2. DB 데이터는 import 디렉토리 안의 임포트.sql 을 import 한다.
   - 일부 명령어(`REM INSERTING into PROJECT.EXWISHLIST`, `SET DEFINE OFF`) 때문에 sql developer 를 이용하는 것을 추천한다.
3. `GuestHouseApplication.main()`을 실행한다.

## 컨벤션
- 해당 [컨벤션](https://github.com/soosue/my_guesthouse/wiki/Convention) 을 바탕으로 작성된다.
