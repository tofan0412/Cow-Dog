package com.xy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xy.entity.ChatRoomJoin;
import com.xy.entity.Member;

@Repository
public interface ChatRoomJoinRepository extends JpaRepository<ChatRoomJoin, Long>{

	List<ChatRoomJoin> findChatRoomJoinByMember(Member member);
}
