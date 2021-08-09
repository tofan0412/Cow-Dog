package com.xy.controller;

import com.xy.domain.ChatMessage;
import com.xy.domain.ChatRoom;
import com.xy.repository.ChatRoomRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate simpMessagingTemplate;
//    private final ChatMessageService chatMessageService;
    
    /*
     * 채팅방 관리 컨트롤러
     */
    //채팅방 유저 입장 알림
    @MessageMapping("/cowdog/chat/enter")
    public void enter(ChatMessage message) {
    	message.setMessage(message.getWriter() + "님이 채팅방에 입장 했습니다.");
    	simpMessagingTemplate.convertAndSend("/cowdog/topic/" + message.getChatRoomId(), message);
    }
    
    //메세지 전송
    @MessageMapping("/cowdog/chat/send")
    public void sendMsg(ChatMessage message) throws Exception {
//        String receiver = message.getReceiver();
//        chatMessageService.save(message);
        simpMessagingTemplate.convertAndSend("/cowdog/topic/" + message.getChatRoomId(), message);
    }
    
    
    /*
     * 채팅방 관리 컨트롤러
     */
    private final ChatRoomRepository repository;
    
    //채팅방 목록 조회
    @GetMapping(value = "/cowdog/chat/rooms")
    public ModelAndView rooms() {
    	ModelAndView mv = new ModelAndView("/cowdog/chat/rooms");
    	
    	mv.addObject("list", repository.findAllRoom());
    	
    	return mv;
    }
    //채팅방 개설
    @PostMapping("/cowdog/chat/room")
    public String create(@RequestParam String name, RedirectAttributes rttr) {
    	rttr.addFlashAttribute("roomName", repository.createChatRoom(name));
    	return "redirect:/cowdog/chat/rooms";
    }

    //채팅방 이름으로 조회
    @GetMapping("/cowdog/chat/room")
    public void getRoom(String roomId, Model model) {
    	model.addAttribute("room", repository.findRoomById(roomId));
    }
}
//@Slf4j
//@Controller
//@RequiredArgsConstructor
//public class ChatController {
//    private final ChatRoomRepository chatRoomRepository;
//
//    @GetMapping("/")
//    public String rooms(Model model){
//        model.addAttribute("rooms",chatRoomRepository.findAllRoom());
//        return "rooms";
//    }
//
//    @GetMapping("/rooms/{id}")
//    public String room(@PathVariable String id, Model model){
//        ChatRoom room = chatRoomRepository.findRoomById(id);
//        model.addAttribute("room",room);
//        return "room";
//    }
//
//    @GetMapping("/new")
//    public String make(Model model){
//        ChatRoomForm form = new ChatRoomForm();
//        model.addAttribute("form",form);
//        return "newRoom";
//    }
//
//    @PostMapping("/room/new")
//    public String makeRoom(ChatRoomForm form){
//        chatRoomRepository.createChatRoom(form.getName());
//
//        return "redirect:/";
//    }
//
//}
