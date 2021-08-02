package com.xy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xy.entity.UserReport;

@Repository
public interface UserReportRepository extends JpaRepository<UserReport, Long> {
	
}
