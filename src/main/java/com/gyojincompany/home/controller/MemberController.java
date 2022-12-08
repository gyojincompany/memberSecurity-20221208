package com.gyojincompany.home.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gyojincompany.home.dto.MemberDto;
import com.gyojincompany.home.entity.Member;
import com.gyojincompany.home.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/join")
	public String join(Model model) {
		
		MemberDto memberDto = new MemberDto();
		model.addAttribute("memberDto", memberDto);
		
		return "joinForm";
	}
	
	@RequestMapping(value = "/joinOk")
	public String joinOk(MemberDto memberDto) {
		
		Member member = Member.createMember(memberDto, passwordEncoder);
		memberService.saveMember(member);		
		return "index";
	}
}
