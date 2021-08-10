package com.xy.service;

import java.util.List;

import javax.persistence.Query;

import com.xy.entity.Follow;

public interface FollowService {
	public Follow save(Follow follow);
	public List<Follow> getMemberId(long id);
	public List<Follow> amIFollowed(long id);
	public int unFollow(long memberid, long followid);
}
