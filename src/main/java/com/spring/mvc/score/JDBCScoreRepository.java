package com.spring.mvc.score;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("jr")
public class JDBCScoreRepository implements ScoreRepository {

    //DB접속정보 설정
    private String uid = "java01";
    private String upw = "1234";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //데이터베이스가 어디있는지 주소
    private String driverName = "oracle.jdbc.driver.OracleDriver";


    @Override
    public boolean save(Score score) {

        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, uid, upw);

            String sql = "INSERT INTO score " +
                    "VALUES (seq_score.nextval, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, score.getName());
            pstmt.setInt(2, score.getKor());
            pstmt.setInt(3, score.getEng());
            pstmt.setInt(4, score.getMath());
            pstmt.setInt(5, score.getTotal());
            pstmt.setDouble(6, score.getAverage());
            pstmt.setString(7, score.getGrade().toString());

            int result = pstmt.executeUpdate(); // 성공한 쿼리의 수 리턴
            if (result == 1) System.out.println("입력 성공!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Score> findAll() {

        List<Score> scoreList = new ArrayList<>();

        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, uid, upw);

            String sql = "SELECT * FROM score";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                scoreList.add(new Score(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return scoreList;
    }

    @Override
    public Score findOne(int stuNum) {
        try {
            Class.forName(driverName);
            Connection conn = DriverManager.getConnection(url, uid, upw);

            String sql = "SELECT * FROM score WHERE stu_num = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, stuNum);

            //3-2. SQL 실행 명령
            // a : INSERT, UPDATE, DELETE - executeUpdate();
            // b : SELECT                 - executeQuery();
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { //마우스 커서 이동 (행 커서)
                return new Score(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean remove(int stuNum) {
        try {
            //1. 드라이버 로딩
            Class.forName(driverName);
            //2. 연결정보 객체 생성
            Connection conn = DriverManager.getConnection(url, uid, upw);

            //3. SQL실행객체 생성
            String sql = "DELETE FROM score WHERE stu_num = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //3-1. ?값을 채우기 (순번은 1번부터 시작)
            pstmt.setInt(1, stuNum);

            //3-2. SQL 실행 명령
            // a : INSERT, UPDATE, DELETE - executeUpdate();
            // b : SELECT                 - executeQuery();
            int result = pstmt.executeUpdate(); // 성공한 쿼리의 수 리턴
            if (result == 1) System.out.println("삭제 성공!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
