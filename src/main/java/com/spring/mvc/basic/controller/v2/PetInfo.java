package com.spring.mvc.basic.controller.v2;

import lombok.*;

import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor @AllArgsConstructor
public class PetInfo {

    private String kind; //종
    private String name; //이름
    private int age;
    private boolean injection; //예방접종여부
    private List<String> hobby; //좋아하는것
}
