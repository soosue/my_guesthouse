1. `MemberService` 인터페이스가 존재하니깐, 어떤 주요 기능들이 존재하는지 파악하는데 도움이 되었다. 없었다면 서비스 클래스의 메소드들을 한 번 보면 되었을 것이다.
2. `JPA`로 바꾸고 싶다. `Member`를 바꾸자. `Member`가 의존성이 아마 제일 적을 것이라 판단하여 첫 리팩토링 대상으로 선택했다.
3. `Member 저장`이 주요 기능이라고 생각했다. `이메일 확인`, `로그인`, `카카오 로그인`은 아직은 중요하지 않다.(`TDD`대상이 아닌 것 같다.)
4. `Member`를 테스트하려다 보니 `String memberLevel` 필드가 눈에 띄었다. 문서가 없다보니, 해당 값이 어떻게 관리되는지 파악하기 쉽지 않았다. 전체 코드를 살펴보다 `jsp파일`에서 아래 값들을 보고 나서야 어떤 값들이 있는지 알 수 있었다. `enum`을 사용하면 해당 코드를 찾지 않았어도 바로 어떤 값들이 관리되는지 알 수 있었겠다는 생각이 들었다.
```
   <select name = "memberLevel">
   <option value = "">선택하세요</option>
   <option value = "Admin">관리자</option>
   <option value = "A">일반</option>
   <option value="Host">호스트</option>
```
5. `enum Level`을 `Member` 내부에 위치시키는건 어떨까? `Member`의 `Level`이라는 의미를 부여할 수 있을 것 같아 괜찮다는 생각이 듬. 사용할 때 불편하려나?
6. 