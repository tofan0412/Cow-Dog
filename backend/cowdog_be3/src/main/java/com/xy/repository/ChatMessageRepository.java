package com.xy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xy.entity.ChatMessage;

import antlr.collections.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{
}
