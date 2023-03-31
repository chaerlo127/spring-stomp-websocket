//package stomp.chat.webSocket;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.CloseStatus;
//import org.springframework.web.socket.WebSocketMessage;
//import org.springframework.web.socket.WebSocketSession;
//import org.springframework.web.socket.handler.TextWebSocketHandler;
//import stomp.chat.webSocket.dto.ChatMessage;
//import stomp.chat.webSocket.dto.ChatRoom;

/**
 * [STOMP에서는 사용하지 않는 클래스]
 * stomp에서는 따로 ChatHandler를 생성할 필요 없음
 * MessageMapping으로 관리가능
 */
//@RequiredArgsConstructor
//@Component
//@Slf4j
//public class ChatHandler extends TextWebSocketHandler {
//
//    /**
//     * JSON을 JAVA로,JAVA를 JSON으로 serialization을 할 때 사용하는 Jackson 라이브러리
//     *
//     * JAVA -> JSON : writeValue()
//     * JSON -> JAVA : readValue()
//     */
//    private final ObjectMapper mapper;
//    private final ChatService service;
//
//    /**
//     * payload: 전송되는 데이터, 보내고자하는 데이터 자체
//     * 채팅서비스에서는 보내고자하는 message가 payload
//     */
//    @Override
//    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
//        String payload = (String) message.getPayload();
//        log.info("payload : {}",payload);
//
//        ChatMessage chatMessage = mapper.readValue(payload, ChatMessage.class);
//        log.info("session : {}",chatMessage.toString());
//
//        ChatRoom room = service.findRoomById(chatMessage.getRoomId());
//        log.info("room : {}",room.toString());
//
//        room.handleAction(session,chatMessage, service);
//    }
//
//    /** Client가 접속 시 호출되는 메서드*/
//    @Override
//    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        log.info(session + " 클라이언트 접속");
//    }
//
//    /** client가 접속 해제되면 호출되는 메서드*/
//    @Override
//    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        log.info(session + " 클라이언트 접속 해제");
//    }
//
//    /** client가 접속 해제되면 호출되는 메서드*/
//    @Override
//    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
//        super.handleTransportError(session, exception);
//    }
//}
