package com.spring.mvc.score;

import java.util.List;

public interface ScoreRepository {

    //점수 저장
    boolean save(Score score);

    //전체 점수 정보 조회
    List<Score> findAll();

    //개별 점수 정보 조회
    Score findOne(int stuNum);

    //점수 삭제
    boolean remove(int stuNum);

}
