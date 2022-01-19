package com.spring.mvc.score;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("mr")
public class MemoryScoreRepository implements ScoreRepository {

    private static final Map<Integer, Score> scoreMap = new HashMap<>();

    static {
        scoreMap.put(1, new Score("홍길동", 98, 70, 50));
        scoreMap.put(2, new Score("박영희", 60, 90, 100));
        scoreMap.put(3, new Score("김철수", 93, 40, 80));
    }

    @Override
    public boolean save(Score score) {
        scoreMap.put(score.getStuNum(), score);
        return true;
    }

    @Override
    public List<Score> findAll() {
//        List<Score> scoreList = (List<Score>) scoreMap.values();
        List<Score> scoreList = new ArrayList<>();
        for (Integer key : scoreMap.keySet()) {
            scoreList.add(scoreMap.get(key));
        }
        return scoreList;
    }

    @Override
    public Score findOne(int stuNum) {
        return scoreMap.get(stuNum);
    }

    @Override
    public boolean remove(int stuNum) {
        scoreMap.remove(stuNum);
        return true;
    }
}
