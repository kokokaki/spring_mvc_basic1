package com.spring.mvc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBaseCRUD {

    //DB접속정보 설정
    private String uid = "java01";
    private String upw = "1234";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //데이터베이스가 어디있는지 주소
    private String driverName = "oracle.jdbc.driver.OracleDriver";

    @Test
    void connectTest() {

        try {
            //1. 드라이버 클래스 동적 로딩
            Class.forName(driverName);

            //2. 연결정보 객체 생성
            Connection connection = DriverManager.getConnection(url, uid, upw);
            System.out.println("DB 접속 성공!");

        } catch (Exception e) {
            System.out.println("DB 접속 실패!");
            e.printStackTrace();
        }

    }


    @Test
    @DisplayName("회원 데이터를 저장할 수 있다.")
    void insertTest() {

        try {
            //1. 드라이버 로딩
            Class.forName(driverName);
            //2. 연결정보 객체 생성
            Connection conn = DriverManager.getConnection(url, uid, upw);

            //3. SQL실행객체 생성
            String sql = "INSERT INTO tbl_member VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //3-1. ?값을 채우기 (순번은 1번부터 시작)
            pstmt.setString(1, "kkk9999");
            pstmt.setString(2, "gfgfgfg1@#");
            pstmt.setString(3, "김제이디비씨");
            pstmt.setString(4, "M");
            pstmt.setInt(5, 50);

            //3-2. SQL 실행 명령
            // a : INSERT, UPDATE, DELETE - executeUpdate();
            // b : SELECT                 - executeQuery();
            int result = pstmt.executeUpdate(); // 성공한 쿼리의 수 리턴
            if (result == 1) System.out.println("입력 성공!");

        } catch (Exception e) {

        }
    }

    @Test
    @DisplayName("회원 정보를 삭제해야 한다.")
    void deleteTest() {
        try {
            //1. 드라이버 로딩
            Class.forName(driverName);
            //2. 연결정보 객체 생성
            Connection conn = DriverManager.getConnection(url, uid, upw);

            //3. SQL실행객체 생성
            String sql = "DELETE FROM tbl_member WHERE account = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //3-1. ?값을 채우기 (순번은 1번부터 시작)
            pstmt.setString(1, "zzz1234");

            //3-2. SQL 실행 명령
            // a : INSERT, UPDATE, DELETE - executeUpdate();
            // b : SELECT                 - executeQuery();
            int result = pstmt.executeUpdate(); // 성공한 쿼리의 수 리턴
            if (result == 1) System.out.println("삭제 성공!");

        } catch (Exception e) {

        }
    }

    @Test
    @DisplayName("회원의 이름과 나이를 수정할 수 있다.")
    void updateTest() {
        try {
            //1. 드라이버 로딩
            Class.forName(driverName);
            //2. 연결정보 객체 생성
            Connection conn = DriverManager.getConnection(url, uid, upw);

            //3. SQL실행객체 생성
            String sql = "UPDATE tbl_member " +
                         "SET username=?, age=? " +
                         "WHERE account = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //3-1. ?값을 채우기 (순번은 1번부터 시작)
            pstmt.setString(1, "수정한이름");
            pstmt.setInt(2, 999);
            pstmt.setString(3, "ghi1234");

            //3-2. SQL 실행 명령
            // a : INSERT, UPDATE, DELETE - executeUpdate();
            // b : SELECT                 - executeQuery();
            int result = pstmt.executeUpdate(); // 성공한 쿼리의 수 리턴
            System.out.println("result = " + result);
            if (result == 1) System.out.println("수정 성공!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    @DisplayName("회원정보를 전체조회해야 한다.")
    void selectAllTest() {
        try {
            //1. 드라이버 로딩
            Class.forName(driverName);
            //2. 연결정보 객체 생성
            Connection conn = DriverManager.getConnection(url, uid, upw);

            //3. SQL실행객체 생성
            String sql = "SELECT * FROM tbl_member";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //3-1. ?값을 채우기 (순번은 1번부터 시작)

            //3-2. SQL 실행 명령
            // a : INSERT, UPDATE, DELETE - executeUpdate();
            // b : SELECT                 - executeQuery();
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) { //마우스 커서 이동 (행 커서)

                //컬럼 데이터 추출하기 (추출데이터에 해당하는 열 이름)
                String username = rs.getString("username");
                System.out.println("username = " + username);
                int age = rs.getInt("age");
                System.out.println("age = " + age);
                System.out.println("===================================");
            }

        } catch (Exception e) {

        }
    }

    @Test
    @DisplayName("회원 1명의 모든정보를 조회하고 출력해야 한다.")
    void selectOneTest() {
        try {
            //1. 드라이버 로딩
            Class.forName(driverName);
            //2. 연결정보 객체 생성
            Connection conn = DriverManager.getConnection(url, uid, upw);

            //3. SQL실행객체 생성
            String sql = "SELECT * FROM tbl_member WHERE account = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //3-1. ?값을 채우기 (순번은 1번부터 시작)
            pstmt.setString(1, "def1234");

            //3-2. SQL 실행 명령
            // a : INSERT, UPDATE, DELETE - executeUpdate();
            // b : SELECT                 - executeQuery();
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) { //마우스 커서 이동 (행 커서)

                //컬럼 데이터 추출하기 (추출데이터에 해당하는 열 이름)
                String account = rs.getString("account");
                String password = rs.getString("password");
                String username = rs.getString("username");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");
                System.out.println("===================================");

                System.out.printf("%s, %s, %s, %s, %d\n", account, password, username, gender, age);
            }

        } catch (Exception e) {

        }
    }


}
