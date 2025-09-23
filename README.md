## DIP(Dependency Inversion Principle, 의존성 역전 원칙)

1. 상위 레벨 모듈은 하위 수준의 모듈에 의존해서는 안된다. 모두 추상화에 의존해야 한다.(High level modules should not depend on low level modules. Both should depend on abstractions.)
2. 추상화는 구체적인 사항에 의존해서는 안된다. 구체적인 사항은 추상화에 의존해야 한다.(Abstractions should not depend on details. Details should depend on abstractions.)

### 상위 레벨 모듈은 하위 수준의 모듈에 의존해서는 안된다

상위 레벨 모듈(Policy Layer) 즉, payment 모듈은 하위 수준(Mechanism Layer) 즉, exchangerate 모듈에 의존해서는 안된다.

- 현재 payment.PaymentService는 exchangerate.ExchangeRateProvider를 의존하는데, 상위 레벨 모듈은 하위 레빌 모듈에 의존하고 있다.
- 따라서 인터페이스 소유권의 역전(인터페이스 모듈 위치 변경)이 필요하다. exchangeRateProvider 인터페이스는 payment 모듈에 존재하면 DIP 원칙을 만족한다.

## 테스트(Test)

### 수동 테스트의 한계(Limitation of Manual Test)

1. 프린트된 메시지를 수동으로 확인하는 방법은 불편하다.
2. 사용자 웹 UI까지 개발한 뒤에 확인하는 방법은 테스트가 실패했을 때 확인할 코드가 많다.
3. 테스트할 대상이 많아질수록 검증하는데 시간이 많이 걸리고 부정확함

### 작은 크기의 자동 수행되는 테스트(Automated Test)

개발자가 만드는 테스트

- 개발한 코드에 대한 검증 기능을 코드로 작성
- 자동으로 테스트를 수행하고 결과를 확인
- 테스팅 프레임워크(JUnit 5) 활용
- 테스트 작성과 실행도 개발 과정의 일부

### JUnit 5

- `@Test` 테스트 메서드
- `@BeforeEach` 테스트 메서드 실행 전에 실행할 코드
- 각 테스트 전에 실행된다.
- 테스트마다 각 새로운 인스턴스를 생성한다.