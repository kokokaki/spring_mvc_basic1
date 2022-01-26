package com.spring.mvc.member.controller;

import com.spring.mvc.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입 화면 요청
    @GetMapping("/join")
    public String join() {
        return "member/join";
    }

    //아이디 중복확인 비동기 통신요청
    @GetMapping("/check")
    @ResponseBody
    public boolean check(String checkId) {
        return memberService.isDuplicateId(checkId);
    }
    @GetMapping("/check2")
    @ResponseBody
    public boolean check2(String checkEmail) {
        return memberService.isDuplicateEmail(checkEmail);
    }
}