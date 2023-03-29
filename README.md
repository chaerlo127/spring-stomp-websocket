# webSocket
* 기존의 단방향 HTTP 프로토콜과 호환되어 양방향 통신을 제공하기 위해 개발된 프로토콜
* 방화벽에 제약이 없으며 통상 WebSocket으로 불림
* 클라이언트가 접속 요청을 하고, 웹 서버가 응답한 후에 연결을 끊는 것이 아닌 connection 을 그대로 유지하고 클라이언트의 요청이 없어도 데이터를 전송할 수 있는 프로토콜
* 실시간을 보장하고, 변경 사항의 빈도가 잦을 때 주로 사용

# webSocket 열기 HandShake
클라이언트가 먼저 핸드셰이크 요청을 보내고 이에 응답을 서버가 클라이언트로 보내는 구조


[출처]
1. https://dev-gorany.tistory.com/212