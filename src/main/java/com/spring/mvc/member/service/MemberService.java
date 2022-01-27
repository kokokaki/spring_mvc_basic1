package com.spring.mvc.member.service;

import com.spring.mvc.member.domain.Member;
import com.spring.mvc.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.spring.mvc.member.service.LoginFlag.*;

@Service
@RequiredArgsConstructor //파이널 필드 초기화 생성자 자동생성
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입 중간처리
    public void signUp(Member member) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePw = encoder.encode(member.getPassword());
        member.setPassword(encodePw);

        memberRepository.register(member);
    }
    //아이디 중복확인
    public boolean isDuplicateId(String account) {
        return memberRepository.isDuplicateId(account) == 1;
    }
    //이메일 중복확인
    public boolean isDuplicateEmail(String email) {
        return memberRepository.isDuplicateEmail(email) == 1;
    }

    //로그인 중간처리
    public LoginFlag login(String inputId, String inputPw) {
        Member member = memberRepository.findMember(inputId);
        if (member != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder.matches(inputPw, member.getPassword()) ? SUCCESS : NO_PW;
        } else {
            //가입 안함
            return NO_ID;
        }
    }

    //회원정보 가져오기
    public Member getMember(String account) {
        return memberRepository.findMember(account);
    }


}
