package com.xy.service;

import java.util.List;

import com.xy.entity.Follow;

public interface FollowService {
	public Follow save(Follow follow);
	public List<Follow> getMemberId(long id);
}
