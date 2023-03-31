package stomp.chat.webSocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import stomp.chat.webSocket.dto.ChatMessage;

@RestController
@RequiredArgsConstructor
public class ChatController {
    /**
     * 약속된 경로나 사용자에게 메시지 전달할 수 있게 해줌.
     */
    private final SimpMessageSendingOperations operations;
//    option 대신에 사용 가능
//    private final SimpMessagingTemplate template;

    // pub/open
    @MessageMapping("/open")
    public void message(ChatMessage chatMessage){
        // sub/channel/{channelId}에 구독 중인 클라이언트들에게 전송
        operations.convertAndSend("/sub/channel/" + chatMessage.getChannelId(), chatMessage);
    }
}
