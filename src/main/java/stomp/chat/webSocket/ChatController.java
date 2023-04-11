package stomp.chat.webSocket;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import stomp.chat.webSocket.dto.ChatMessage;

@RestController
@RequiredArgsConstructor
public class ChatController {
    /**
     * 약속된 경로나 사용자에게 메시지 전달할 수 있게 해줌.
     */
    private final SimpMessagingTemplate template;
//    option 대신에 사용 가능
//    private final SimpMessagingTemplate template;

    // pub/open
    @MessageMapping(value = "/enter")
    public void enter(ChatMessage message){
        message.setMessage(message.getSender() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/rooms/" + message.getChannelId(), message.getMessage());
    }

    @MessageMapping(value = "/message")
    public void message(ChatMessage message){
        message.setMessage(message.getSender() + "님이 메시지를 작성했습니다.");
        template.convertAndSend("/sub/rooms/" + message.getChannelId(), message.getMessage());
    }
}
