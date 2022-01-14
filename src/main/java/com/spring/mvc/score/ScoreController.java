package com.spring.mvc.score;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Log4j2
public class ScoreController {

    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreController(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }


    //점수 입력화면 열기
    @GetMapping("/score/form")
    public String form() {
        return "score/score-list";
    }

    //점수 전체조회 요청처리
    @GetMapping("/score/list")
    public String list(Model model) {
        log.info("/score/list GET !");
        List<Score> scoreList = scoreRepository.findAll();
        model.addAttribute("scores", scoreList);
        return "score/score-list";
    }

    //점수 등록 요청처리
    @PostMapping("/score/register")
    public String register(Score score) {
        log.info("/score/register POST - " + score);
        score.calcTotal(); // 총점, 평균 계산
        score.changeMarkName(); //마킹 네임 저장
        scoreRepository.save(score);
        return "redirect:/score/list";
    }

    //점수 삭제 요청 처리
    @GetMapping("/score/delete")
    public String delete(int stuNum) {
        log.info("/score/delete GET - " + stuNum);
        scoreRepository.remove(stuNum); //삭제 명령 위임
        return "redirect:/score/list";
    }
}
