package com.spring.mvc.basic.controller.v2;

import lombok.*;

@Setter @Getter @ToString
@AllArgsConstructor @NoArgsConstructor
public class BmiData {

    private String name;
    private double height;
    private double weight;
}
