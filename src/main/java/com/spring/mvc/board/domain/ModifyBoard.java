package com.spring.mvc.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class ModifyBoard {

    private int boardNo;
    private String title;
    private String content;
    private String writer;
}
