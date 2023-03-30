package stomp.webSocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration // container 등록
@EnableWebSocket // websocket 사용서버, websocket 활성화
@RequiredArgsConstructor
@Slf4j
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatHandler chatHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(chatHandler, "ws/chat").setAllowedOrigins("*"); // endpoint : /ws/chat
    }


//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/websocket").setAllowedOrigins("*").withSockJS(); // 소켓 엔드포인트 지정하는 코드
//        // ws://localhost:8080/websocket
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        /**
//         * /topic/hello 라는 토픽에 대해 구독을 신청하면 /test/topic/hello를 의미
//         */
//        config.setApplicationDestinationPrefixes("/test"); // 도착 경로
//
//        /**
//         * topic: 한명이 message를 발행했을 때 해당 토픽을 구독하고 있는 n 명에게 뿌릴 때
//         * queue: 한명이 message를 발행했을 때 발행한 한 명에게 정보를 다시 보내는 경우에 사용
//         *
//         */
//        config.enableSimpleBroker("/topic", "/queue");
//    }
}
