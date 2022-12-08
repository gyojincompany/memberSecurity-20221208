package com.gyojincompany.home.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
		memberDto.setMid("tiger333");
		memberDto.setMname("홍길동");
		memberDto.setMpw("12345");
		memberDto.setMemail("abc@abc.com");
		
		
		
		return Member.createMember(memberDto, passwordEncoder);
		
	}
	public Member createMember2() {
		
		MemberDto memberDto = new MemberDto();
		memberDto.setMid("fireDog22");
		memberDto.setMname("김유신");
		memberDto.setMpw("23456");
		memberDto.setMemail("kjl@abc.com");
		
		
		
		return Member.createMember(memberDto, passwordEncoder);
		
	}
	@Test
	@DisplayName("회원가입 테스트")
	public void saveMemberTest() {
		Member member1 = createMember();		
		Member savedMember = memberService.saveMember(member1);
		assertEquals(member1.getMid(), savedMember.getMid());
	}
	
	@Test
	@DisplayName("중복 회원 가입 테스트")
	public void duplicateMemberTest() {
		Member member1 = createMember2();
		Member member2 = createMember2();
		
		memberService.saveMember(member1);//fireDog 가입
		
		Throwable e = assertThrows(IllegalStateException.class, () -> {
		memberService.saveMember(member2);});//테스트 예외처리
		
		System.out.println(e.getMessage());
		assertEquals("이미 가입된 회원입니다!", e.getMessage());
	}
	
}
