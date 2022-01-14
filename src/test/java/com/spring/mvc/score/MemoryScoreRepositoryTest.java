package com.spring.mvc.score;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryScoreRepositoryTest {

    MemoryScoreRepository memoryScoreRepository = new MemoryScoreRepository();

    @Test
    @DisplayName("성적정보를 추가하고 전체 조회할 수 있다.")
    void addTest() {
        memoryScoreRepository.save(new Score("짹짹이", 99, 99, 100));
        memoryScoreRepository.save(new Score("야옹이", 33, 22, 44));

        List<Score> scoreList = memoryScoreRepository.findAll();
        for (Score score : scoreList) {
            System.out.println(score);
        }
    }

    @Test
    @DisplayName("학번을 통해 데이터를 개별조회하고 삭제할 수 있다.")
    void findOneTest() {

        //3번 학번 학생을 삭제
        memoryScoreRepository.remove(3);

        //3번 학번 학생을 조회 -> 조회실패(null)
        Score score = memoryScoreRepository.findOne(3);
        System.out.println(score == null);
        System.out.println(score);

        Assertions.assertNull(score);
    }
}