package com.spring.mvc.member.repository;

import com.spring.mvc.member.domain.Auth;
import com.spring.mvc.member.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //스프링컨테이너에서 객체를 주입받아 테스트
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입에 성공해야 한다.")
    void regTest() {

        Member member = new Member();
        member.setAccount("def1234");

        //암호화 적용
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePassword = encoder.encode("aaa1111");
        member.setPassword(encodePassword);

        member.setName("김철수");
        member.setEmail("def@naver.com");
        member.setAuth(Auth.COMMON);

        memberRepository.register(member);
    }

    @Test
    @DisplayName("아이디, 이메일 중복확인에 성공해야 한다.")
    void dupleTest() {
        String inputId = "def1234";
        int result = memberRepository.isDuplicateId(inputId);
        assertEquals(1, result);

        String inputEmail = "dfsdfs@gmail.com";
        int result2 = memberRepository.isDuplicateEmail(inputEmail);
        assertEquals(0, result2);

    }


}