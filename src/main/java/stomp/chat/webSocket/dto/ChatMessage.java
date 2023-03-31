package stomp.chat.webSocket.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ChatMessage {
    // enter, talk 뿐만 아니라, out 도 추가하여 나간 채팅인 것도 있음 좋을 것 같다.
    public enum MessageType{
        ENTER, TALK
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
    private String time;


}
