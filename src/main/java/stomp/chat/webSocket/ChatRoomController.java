package stomp.chat.webSocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import stomp.chat.webSocket.dto.ChatRoom;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
    private final ChatService chatService;
    @PostMapping
    public ChatRoom createRoom(@RequestParam String name){
        return this.chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRooms(){
        return chatService.findAllRoom();
    }
}
