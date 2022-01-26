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

    @Test
    @DisplayName("로그인 검증을 정확히 수행해야 한다.")
    void loginTest() {

        //로그인 시도 아이디, 비밀번호
        String inputId = "def1234", inputPw = "aaa1111";

        //아이디를 가지고 회원정보 조회
        Member member = memberRepository.findMember(inputId);

        if (member != null) {
            //db에 저장된 비밀번호 조회
            String dbPw = member.getPassword();

            //암호화된 비번을 평문으로 내부적 디코딩 후 비교
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            if (encoder.matches(inputPw, dbPw)) {
                System.out.println("로그인 성공");
            } else {
                System.out.println("비밀번호가 틀렸습니다.");
            }
        } else {
            System.out.println("가입된 아이디가 아닙니다.");
        }

    }


}