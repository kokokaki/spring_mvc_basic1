package com.spring.mvc.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class Board {

    private static int sequence;

    private int boardNo; //글번호
    private String writer; //작성자
    private String title; //글제목
    private String content; //글내용
    private int viewCnt; //조회수
    private Timestamp regDate; //글작성 일자시간

    public Board() {
        System.out.println("board constructor call!!");
        this.boardNo = ++sequence;
    }

    public Board(ResultSet rs) throws SQLException {
        this.boardNo = rs.getInt("board_no");
        this.writer = rs.getString("writer");
        this.title = rs.getString("title");
        this.content = rs.getString("content");
        this.viewCnt = rs.getInt("view_cnt");
        this.regDate = rs.getTimestamp("reg_date");
    }

    public Board(String writer, String title, String content) {
        this();
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}