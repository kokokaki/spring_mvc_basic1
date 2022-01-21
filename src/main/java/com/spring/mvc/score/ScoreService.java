package com.spring.mvc.score;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    //JDBC 사용
    /*
    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(@Qualifier("jr") ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }
     */

    //마이바티스 사용
    private final ScoreMapper scoreRepository;

    @Autowired
    public ScoreService(ScoreMapper scoreRepository) {
        this.scoreRepository = scoreRepository;
    }


    //점수 전체조회 요청 중간처리
    public List<Score> findAll() {
        List<Score> scoreList = scoreRepository.findAll();

        //repo가 가져온 데이터 중간처리
        // .............

        return scoreList;
    }

    //점수 등록 요청 중간처리
    public void save(Score score) {
        score.calcTotal(); // 총점, 평균 계산
        score.changeMarkName(); //마킹 네임 저장
        scoreRepository.save(score);
    }

    //점수 삭제 요청 중간처리
    public void remove(int stuNum) {
        scoreRepository.remove(stuNum);
    }

    //점수 개별조회 요청 중간처리리
    public Score findOne(int stuNum) {
        Score score = scoreRepository.findOne(stuNum);
        //중간처리 ...........
        return score;
    }

}
