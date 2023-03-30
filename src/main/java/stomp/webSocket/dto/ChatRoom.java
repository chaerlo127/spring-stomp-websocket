package stomp.webSocket.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;
import stomp.webSocket.ChatService;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {
    private String roodId;
    private String name;
    // 어떤 사람이 있어야 하는지 저장하는 attribute
    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(String roomId, String name){
        this.roodId = roomId;
        this.name =  name;
    }

    // message type 에 따라 session 에게 메시지 전달하기 위한 메소드

    public void handleAction(WebSocketSession session, ChatMessage chat, ChatService chatService){
        if(chat.getType().equals(ChatMessage.MessageType.ENTER)) {
            sessions.add(session);

            chat.setMessage(chat.getSender() + " 님이 입장하셨습니다"); // 입장한 것을 전달
            sendMessage(chat, chatService);
        }else{
            chat.setMessage(chat.getMessage()); // talk : 메시지를 받아서 전달
            sendMessage(chat, chatService);
        }
    }

    /**
     * session에 담긴 모든 session 에 handleAction으로 부터 넘어온 message를 전달할 수 있는 메소드
     * 즉, 채팅방에 있는 모든 사람들에게 변경된 내용(enter, talk)을 전달
     */
    public <T> void sendMessage(T message, ChatService service){
        sessions.parallelStream().forEach(sessions -> service.sendMessage(sessions,message));
    }
}
