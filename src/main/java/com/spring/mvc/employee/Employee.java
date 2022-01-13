package com.spring.mvc.employee;

import lombok.*;

@Setter @Getter @ToString
@AllArgsConstructor
public class Employee {

    //연속된 일련번호
    private static int seq;

    private int empNum; //사원번호
    private String empName; //사원명
    private String position; //직급

    public Employee() {
        this.empNum = ++seq;
    }

    public Employee(String empName, String position) {
        this();
        this.empName = empName;
        this.position = position;
    }
}
