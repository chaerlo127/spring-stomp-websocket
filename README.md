# 구현 정리

| issue                                                    | 기능                                              |
|------------------------------------------------------------|-------------------------------------------------|
| [1](https://github.com/chaerlo127/websocket-stomp/issues/1) | 😑 `Trouble Shooting`: webSocket을 열 수 없다는 에러 발생 |
| [2](https://github.com/chaerlo127/websocket-stomp/issues/2) | `feature`: websocket 채팅방 기능 구현                  |
| [4](https://github.com/chaerlo127/websocket-stomp/issues/4) | `😑 Trouble Shooting`: Stomp가 연결되지 않는 에러 발생     |
| [5](https://github.com/chaerlo127/websocket-stomp/issues/5) | `feature`: stomp 채팅방 기능 구현                      |


# webSocket
* 기존의 단방향 HTTP 프로토콜과 호환되어 양방향 통신을 제공하기 위해 개발된 프로토콜
* 방화벽에 제약이 없으며 통상 WebSocket으로 불림
* 클라이언트가 접속 요청을 하고, 웹 서버가 응답한 후에 연결을 끊는 것이 아닌 connection 을 그대로 유지하고 클라이언트의 요청이 없어도 데이터를 전송할 수 있는 프로토콜
* 실시간을 보장하고, 변경 사항의 빈도가 잦을 때 주로 사용

### webSocket 열기 HandShake
* 클라이언트가 먼저 핸드셰이크 요청을 보내고 이에 응답을 서버가 클라이언트로 보내는 구조
* HTTP 프로토콜을 통해 연결이 이루어지며, 연결이 정상적으로 이루어지면, HTTP 연결은 자동으로 끊김

### `ws` vs `wss`
* HTTP, HTTPS 통신을 위해 오픈한 포트를 사용하는데, websocket은 별도의 포트를 사용할 필요가 없음
* 보안을 위해 사용하는 HTTPS처럼 웹 소켓 통신도 wss로 통신을 지원함.


### 단점
* 웹 소켓 서버가 1대인 경우에만 정상적으로 동작을 함.
* 항상 연결을 하고 있어야 함. 
* 웹 소켓 서버가 다수인 경우에는 STOMP를 사용

<br> 

# stomp (Simple Text Oriented Messaging Protocol)
* webSocket 위에서 동작하는 양방향 네트워크 프로토콜
* HTTP에서 모델링 되는 Frame 기반 프로토콜
* 클라이언트와 서버가 전송할 메시지의 유형, 형식, 내용들을 정의하는 메커니즘.
* pub / sub 구조로 되어있어 메시지를 전송하고 메시지를 받아 처리하는 부분이 확실히 정해져 있음
  * 개발자 입장에서 명확하게 인지하고 개발 가능
  * pub: 메시지 송신
  * sub: 메시지 수신
* 메세지의 헤더에 값을 줄 수 있어 헤더 값을 기반으로 통신 시 인증처리를 구현하는 것
* `chatHandler를 직접 구현할 필요가 없음`

## `@MessageMapping`
메시지 발행 시 엔드포인트를 별도로 분리

## stomp 형식
```text
COMMAND
header1:value1
header2:value2

Body^@
```
* COMMAND: SEND, SUBSCRIBE
* HEADER: 기존의 WebSocket으로 구현이 불가능한 Header 작성 가능
  * Destination: 메세지를 보내거나 구독을 할 수 있음

### 예시
```text
SUBSCRIBE
destination: /sub/chat/room/5
---------------------------------------
SEND
content-type: application/json
destination: /pub/chat

{"chatRoomId": 5, "type": "MESSAGE", "writer": "clientB"}
---------------------------------------
MESSAGE
destination: /subscribe/chat/room/5

{"chatRoomId": 5, "type": "MESSAGE", "writer": "clientB"}
```

<br> 

**[출처]**
1. https://dev-gorany.tistory.com/212
2. https://dev-gorany.tistory.com/235
3. https://supawer0728.github.io/2018/03/30/spring-websocket/


**[실습출처]**
1. https://velog.io/@jyyoun1022/Spring-Web-Socket%EC%9B%B9-%EC%86%8C%EC%BC%93%EA%B3%BC-Chatting%EC%B1%84%ED%8C%85-2
2. https://brunch.co.kr/@springboot/695