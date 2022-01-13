package com.spring.mvc.basic.controller.v2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicV2Controller {

    //@ResponseBody를 붙이면 문자열이 그대로 브라우저에게 전달됨
    // 떼면 해당 문자열의 경로에 있는 파일을 열어줌
    @GetMapping("/join")
    public String join() {
        return "/register.html"; //열어줄 파일의 경로
    }

    @GetMapping("/gugudan")
    public String gugudan() {
        return "/common/gugu3.html";
    }

    @GetMapping("/gugu7")
    public String gugu7() {
        return "gugu7";
    }

    // 요청 파라미터 읽기(쿼리스트링 읽기): 클라이언트가 서버로 전송한 데이터
    // www.abc.com/v2/hello?pet=dog&name=뽀삐  <-- pet, name 정보 얻어내기
    @GetMapping("/v2/hello")
    public String hello(@RequestParam("p") String pet, int age) {
        System.out.println("/v2/hello 요청이 들어옴~!");
        System.out.println("pet = " + pet);
        System.out.println("age = " + age);
        return "";
    }

    //요청 파라미터가 많을 경우 객체를 이용
    //서버에서 클라이언트로 데이터를 보내는 방법
    //Model 객체를 활용
    @PostMapping("/pet/info")
    public String pet(PetInfo petInfo, Model model) {
        System.out.println(petInfo);
        //jsp파일에게 데이터를 전송
        model.addAttribute("pi", petInfo);
        return "pet-result";
    }

    // pet-form.jsp를 열어주려면?
    @GetMapping("/pet/form")
    public String petForm() {
        return "pet-form";
    }

    //=========== 실습문제 ===============//
    /*
          1. 우선 register.jsp를 열어주는 요청 메서드를 작성하세요.
           URL: /user/form 입니다.
    */

    @GetMapping("/user/form")
    public String userForm() {
        return "register";
    }

    /*
          2. 사용자가 form에 등록한 회원정보를 받아서 처리하는 요청 메서드를
          작성하세요
           URL: /user/join 입니다.
    */
    @PostMapping("/user/join")
    public String join(User user, Model model) {
        System.out.println("/user/join POST! - " + user);
        model.addAttribute("u", user);
        return "user-result";
    }


    /*
          3. 2번 요청이 끝난 후 user-result.jsp로 이동시키시고 해당 파일에서는
          회원의 정보를 출력해주세요
           ex) 아이디는 ??이고 비밀번호는 ??입니다. 이름은 ??이고 ~~~

     */


    //======= bmi 문제 ======//

    @GetMapping("/bmi/form")
    public String bmiForm() {
        return "bmi-form";
    }

    @PostMapping("/bmi/calc")
    public String bmiCalc(BmiData bmiData, Model model) {
        //공식 : kg / (m * m)
        double kg = bmiData.getWeight();
        double m = bmiData.getHeight() / 100;
        double bmi = kg / (m * m);

        //반올림
        bmi = Math.round(bmi * 100) / 100.0;

        model.addAttribute("userName", bmiData.getName());
        model.addAttribute("bmi", bmi);

        return "bmi-result";
    }

}
