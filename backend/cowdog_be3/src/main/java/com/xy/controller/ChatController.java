package com.xy.controller;

import com.xy.api.request.NoticeUpdatePutReq;
import com.xy.common.response.BaseResponseBody;
import com.xy.entity.ChatMessage;
import com.xy.entity.Notice;
import com.xy.repository.MemberRepository;
import com.xy.service.ChatMessageService;
import com.xy.service.ChatRoomJoinService;
import com.xy.service.MemberService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cowdog/chat")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	MemberService memSer;
	
	@Autowired
	MemberRepository memRepo;
	
    @Autowired
    ChatRoomJoinService	chatRoomJoinService;
    
    @Autowired
    ChatMessageService chatMessageSer;
    
    
    //채팅방 개설
    @PostMapping("/newChat")
    public ResponseEntity<? extends BaseResponseBody> newChat(@RequestBody Map<String, String> map){
    	Long chatRoomId = chatRoomJoinService.newRoom(map.get("user1"),map.get("user2"));
    	
        if ( chatRoomId != 0L) {
            // 성공 시 생성된 방 Id 리턴
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, Long.toString(chatRoomId)));
        }
        return ResponseEntity.status(500).body(BaseResponseBody.of(500, "FAIL"));
    }
    
    //@MessageMapping("/chat/room/{id}")
//	 @GetMapping("/chat/room/{id}")
//	 public List<ChatMessage> getMessages(@PathVariable long id) throws Exception {
//		 System.out.println("채팅창 내용 가져오기 요청 왔음");
//		 return chatMessageSer.getChatMessagesByChatroomId(id);
//	 }
    
    //채팅 내용 불러오기
//    @GetMapping("/room")
//    public ResponseEntity<List<ChatMessage>>  getMessages(@RequestParam Map<String, String> map){
//    	 List<ChatMessage> chatMessageEx = chatMessageSer.getChatMessagesByChatroomId(Long.parseLong(map.get("id")));
//    	 
//    	 if (chatMessageEx.size()!=0) {
//    		 return ResponseEntity.status(200).body(chatMessageEx);
//    	 }
//    	 
//    	 return ResponseEntity.status(500).body(chatMessageEx);
//    }
    @GetMapping("/room/{id}")
    public ResponseEntity<List<ChatMessage>>  getMessages(@PathVariable("id") Long id){
    	 List<ChatMessage> chatMessageEx = chatMessageSer.getChatMessagesByChatroomId(id);
    	 
    	 return ResponseEntity.status(200).body(chatMessageEx);
    }
    
//	@DeleteMapping("/{no}") // notice에 no번 공지 delete 요청
//	public List<Notice> delete(@PathVariable("no") Long noticeNo) {
//		noticeService.deleteNotice(noticeNo);
//		return noticeService.getNoticeList();
//	}

}