# 패키지 구조
```
com.juhyo/
├── domain/               # 도메인 모델 (핵심 비즈니스 로직)
│   ├── user/             # 사용자 관련 도메인
│   ├── ingredients/      # 식재료 관련 도메인
│   └── sharing/          # 공유/거래 관련 도메인
│
├── application/          # 애플리케이션 서비스 계층
│   ├── port/             # 포트 (인터페이스) 정의
│   │   ├── in/           # 입력 포트 (use cases)
│   │   │   ├── user/
│   │   │   └── sharing/
│   │   └── out/          # 출력 포트 (persistence, external services)
│   │       ├── user/
│   │       └── sharing/
│   └── service/          # 유스케이스 구현체
│       ├── user/
│       └── sharing/
│
└── adapter/             # 어댑터 (인프라스트럭처 계층)
    ├── in/              # 입력 어댑터
    │   ├── web/         # REST API
    │   │   └── dto/
    │   │       ├── request/
    │   │       └── response/
    │   └── websocket/      # WebSocket (실시간 채팅용)
    └── out/                # 출력 어댑터
        ├── persistence/    # DB 관련
        │   ├── user/
        │   └── sharing/
        └── notification/   # 알림 시스템
```

# Git Commit Convention


| 타입      | 설명                                      |
|-----------|-------------------------------------------|
| `feat`    | 새로운 기능 추가                          |
| `fix`     | 버그 수정                                 |
| `docs`    | 문서만 수정                               |
| `style`   | 코드 스타일 수정 (세미콜론, 공백 등)      |
| `refactor`| 코드 리팩토링 (기능 변경 없음)            |
| `test`    | 테스트 코드 추가 또는 수정                |
| `chore`   | 빌드, 패키지, 설정 파일 수정 등           |
| `perf`    | 성능 개선 관련 수정                       |
| `ci`      | CI 설정 파일 수정                         |
| `build`   | 빌드 시스템이나 외부 의존성 수정          |
