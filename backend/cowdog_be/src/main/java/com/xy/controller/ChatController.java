package com.xy.controller;

import com.xy.common.response.BaseResponseBody;
import com.xy.entity.ChatMessage;
import com.xy.repository.MemberRepository;
import com.xy.service.ChatRoomJoinService;
import com.xy.service.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("/cowdog/chat")
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	MemberService memSer;
	
	@Autowired
	MemberRepository memRepo;
	
    @Autowired
    ChatRoomJoinService	chatRoomJoinService;
    
    
    //채팅방 개설
    @PostMapping("/newChat")
    public ResponseEntity<? extends BaseResponseBody> newChat(@RequestParam("receiver") String user1, @RequestParam("sender") String user2){
    	Long chatRoomId = chatRoomJoinService.newRoom(user1,user2);
    	
        if ( chatRoomId != 0L) {
            // 성공 시 생성된 방 Id 리턴
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, Long.toString(chatRoomId)));
        }
        else {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "FAIL"));
        }
//    	return "redirect:/personalChat/" + chatRoomId; String return 일때의 라인
    }
}