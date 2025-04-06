패키지 구조
```
com.juhyo/
├── domain/                 # 도메인 모델 (핵심 비즈니스 로직)
│   ├── user/              # 사용자 관련 도메인
│   │   ├── User.java
│   │   ├── UserStatus.java
│   │   └── UserRole.java
│   ├── ingredients/        # 식재료 관련 도메인
│   └── sharing/           # 공유/거래 관련 도메인
│
├── application/           # 애플리케이션 서비스 계층
│   ├── port/             # 포트 (인터페이스) 정의
│   │   ├── in/           # 입력 포트 (use cases)
│   │   │   ├── user/
│   │   │   │   ├── RegisterUserUseCase.java
│   │   │   │   ├── FindUserUseCase.java
│   │   │   │   └── UpdateUserUseCase.java
│   │   │   └── sharing/
│   │   └── out/          # 출력 포트 (persistence, external services)
│   │       ├── user/
│   │       │   └── UserPort.java
│   │       └── sharing/
│   └── service/          # 유스케이스 구현체
│       ├── user/
│       │   └── UserService.java
│       └── sharing/
│
└── adapter/              # 어댑터 (인프라스트럭처 계층)
    ├── in/              # 입력 어댑터
    │   ├── web/         # REST API
    │   │   ├── UserController.java
    │   │   └── dto/
    │   │       ├── request/
    │   │       └── response/
    │   └── websocket/   # WebSocket (실시간 채팅용)
    └── out/             # 출력 어댑터
        ├── persistence/ # DB 관련
        │   ├── user/
        │   │   ├── UserRepository.java
        │   │   └── UserJpaEntity.java
        │   └── sharing/
        └── notification/ # 알림 시스템
```
