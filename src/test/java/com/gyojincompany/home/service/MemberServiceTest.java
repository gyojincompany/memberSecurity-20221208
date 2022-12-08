package com.gyojincompany.home.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.gyojincompany.home.dto.MemberDto;
import com.gyojincompany.home.entity.Member;
import com.gyojincompany.home.repository.MemberRepository;

@SpringBootTest
//@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberServiceTest {
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Member createMember() {
		
		MemberDto memberDto = new MemberDto();
		memberDto.setMid("tiger");
		memberDto.setMname("홍길동");
		memberDto.setMpw("12345");
		memberDto.setMemail("abc@abc.com");
		
		
		
		return Member.createMember(memberDto, passwordEncoder);
		
	}
	
	@Test
	@DisplayName("회원가입 테스트")
	public void saveMemberTest() {
		Member member1 = createMember();		
		Member savedMember = memberService.saveMember(member1);
		assertEquals(member1.getMid(), savedMember.getMid());
	}
}
