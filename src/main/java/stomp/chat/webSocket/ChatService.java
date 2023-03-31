package stomp.chat.webSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import stomp.chat.webSocket.dto.ChatRoom;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Data
@Service
@Slf4j
public class ChatService {
    private final ObjectMapper objectMapper;
    private Map<String, ChatRoom> chatRooms; // db 대신, 원래는 db에 넣어두어야 함.

    /**
     * 의존성 주입이 완료된 후에 실행되어야 하는 메소드에서 사용
     * 스프링 컨테이너가 실행된 후에 실행이 되어야 하는 코드
     * 다른 리소스에서 호출되지 않아도 수행
     */
    @PostConstruct
    private void init(){
        chatRooms = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom(){
        return new ArrayList<>(chatRooms.values());
    }

    public ChatRoom findRoomById(String roomId){
        return chatRooms.get(roomId);
    }

    public ChatRoom createRoom(String name){
        String roomId = UUID.randomUUID().toString();

        ChatRoom room = ChatRoom.builder()
                .roomId(roomId)
                .name(name)
                .build();

        chatRooms.put(roomId, room);

        return room;
    }

    public <T> void sendMessage(WebSocketSession session, T message){
        try{
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));;
        }catch (IOException e){
            log.error(e.getMessage(),e);
        }
    }
}
