package com.xy.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xy.entity.ChatMessage;
import com.xy.repository.ChatMessageRepository;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

	@Autowired
	ChatMessageRepository chatMessageRepo;
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public ChatMessage save(ChatMessage chatMessage) {
		return chatMessageRepo.save(chatMessage);
	}

	@Override
	public List<ChatMessage> getChatMessagesByChatroomId(long id) {
//		String jpql="select cm from ChatMessage as cm where cm.room_id.id=:id";
//		TypedQuery<ChatMessage> query=em.createQuery(jpql,ChatMessage.class);
//		query.setParameter("id", id);
//		return query.getResultList();
//		Query q =  em.createNativeQuery("SELECT cm.message_id, cm.member_id, cm.message, cm.time FROM CHAT_MESSAGE cm LEFT JOIN CHAT_ROOM cr where cm.room_id=cr.room_id");
		Query q =  em.createNativeQuery("SELECT * from CHAT_MESSAGE WHERE room_id="+id);
		List<ChatMessage> chatMessageEx = q.getResultList();
		
		return chatMessageEx;
	}

}
