package com.spring.mvc.employee;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryEmpRepositoryTest {

    EmployeeRepository repo = new MemoryEmpRepository();

    @Test
    void repoTest() {
        repo.save(new Employee("김세이브", "사원"));
        repo.save(new Employee("박세이브", "차장"));

        List<Employee> employeeList = repo.findAll();
        for (Employee e : employeeList) {
            System.out.println("e = " + e);
        }
    }
}