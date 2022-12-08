package com.gyojincompany.home;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.gyojincompany.home.dto.MemberDto;
import com.gyojincompany.home.entity.Member;
import com.gyojincompany.home.service.MemberService;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class loginTest {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Member createMember(String mid, String mpw) {
		
		MemberDto memberDto = new MemberDto();
		memberDto.setMid(mid);
		memberDto.setMname("홍길동");
		memberDto.setMpw(mpw);
		memberDto.setMemail("abc@abc.com");
		Member member = Member.createMember(memberDto, passwordEncoder);
		
		Member savedMember = memberService.saveMember(member);//회원가입
		
		return savedMember;		
	}
	
	@Test
	@DisplayName("로그인 성공 테스트")
	public void loginSuccessTest() throws Exception {
		
		String mid = "white84466";
		String mpw = "1234";
		
		createMember(mid, mpw);//black001/1234 계정으로 회원 가입
		
		mockMvc.perform(
				formLogin().userParameter("mid")				
				.loginProcessingUrl("/login")
				.user(mid).password(mpw))
		.andExpect(SecurityMockMvcResultMatchers.authenticated());				
	}
	
//	@Test
//	@DisplayName("로그인 실패 테스트")
//	public void loginFailTest() throws Exception {
//		
//		String mid = "white003";
//		String mpw = "1234";
//		
//		createMember(mid, mpw);//black001/1234 계정으로 회원 가입
//		
//		mockMvc.perform(
//				formLogin().userParameter("mid")
//				.loginProcessingUrl("/login")
//				.user(mid).password("4321"))
//		.andExpect(SecurityMockMvcResultMatchers.authenticated());				
//	}
	
}
