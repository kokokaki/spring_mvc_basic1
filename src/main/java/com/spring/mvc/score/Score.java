package com.spring.mvc.score;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;

@Setter @Getter @ToString
public class Score {
    //누적되는 순차번호를 만들기 위함
    private static int seq;

    // 클라이언트가 전달한 데이터
    private String name; //실명
    private String markName; //마킹된 이름
    private int kor, eng, math;

    //자체 생성 데이터
    private int stuNum; //학번
    private int total;  //총점
    private double average; //평균
    private Grade grade; //학점

    public Score() {
        this.stuNum = ++seq;
    }

    public Score(String name, int kor, int eng, int math) {
        this();
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
        calcTotal();
        changeMarkName();
    }

    //DB조회데이터 처리 생성자
    public Score(ResultSet rs) throws SQLException {
        this.stuNum = rs.getInt("stu_num");
        this.name = rs.getString("stu_name");
        this.kor = rs.getInt("kor");
        this.eng = rs.getInt("eng");
        this.math = rs.getInt("math");
        this.total = rs.getInt("total");
        this.average = rs.getDouble("average");
        this.grade = Grade.valueOf(rs.getString("grade"));
    }

    //총점과 평균, 학점을 구하는 메서드
    public void calcTotal() {
        this.total = this.kor + this.eng + this.math;
        this.average = Math.round(this.total / 3.0 * 100) / 100.0;
        if (this.average >= 90) {
            this.grade = Grade.A;
        } else if (this.average >= 80) {
            this.grade = Grade.B;
        } else if (this.average >= 70) {
            this.grade = Grade.C;
        } else if (this.average >= 60) {
            this.grade = Grade.D;
        } else {
            this.grade = Grade.F;
        }

    }

    //이름 마킹 처리
    public void changeMarkName() {
        //성 추출
        String familyName = String.valueOf(this.name.charAt(0));
        //성을 제외한 이름 수
        int nameCount = name.length() - 1;
        for (int i = 0; i < nameCount; i++) {
            familyName += "*";
        }
        this.markName = familyName;
    }
}
