package com.spring.mvc.employee;

import java.util.List;

public interface EmployeeRepository {

    //저장 기능
    void save(Employee employee);

    //전체 조회 기능
    List<Employee> findAll();

    //삭제 기능
    void remove(int empNum);
}
