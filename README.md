## DIP(Dependency Inversion Principle, 의존성 역전 원칙)

1. 상위 레벨 모듈은 하위 수준의 모듈에 의존해서는 안된다. 모두 추상화에 의존해야 한다.(High level modules should not depend on low level modules. Both should depend on abstractions.)
2. 추상화는 구체적인 사항에 의존해서는 안된다. 구체적인 사항은 추상화에 의존해야 한다.(Abstractions should not depend on details. Details should depend on abstractions.)

### 상위 레벨 모듈은 하위 수준의 모듈에 의존해서는 안된다
상위 레벨 모듈(Policy Layer) 즉, payment 모듈은 하위 수준(Mechanism Layer) 즉, exchangerate 모듈에 의존해서는 안된다.

- 현재 payment.PaymentService는 exchangerate.ExchangeRateProvider를 의존하는데, 상위 레벨 모듈은 하위 레빌 모듈에 의존하고 있다.
- 따라서 인터페이스 소유권의 역전(인터페이스 모듈 위치 변경)이 필요하다. exchangeRateProvider 인터페이스는 payment 모듈에 존재하면 DIP 원칙을 만족한다.