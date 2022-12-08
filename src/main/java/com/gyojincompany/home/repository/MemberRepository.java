package com.gyojincompany.home.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gyojincompany.home.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
	public Member findByMid(String mid);
	
}
