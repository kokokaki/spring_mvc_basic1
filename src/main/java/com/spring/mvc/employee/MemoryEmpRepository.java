package com.spring.mvc.employee;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Log4j2
public class MemoryEmpRepository implements EmployeeRepository {

    //메모리 저장구역
    private static Map<Integer, Employee> employeeMap = new HashMap<>();

    static { //정적 초기화자
        employeeMap.put(1, new Employee("김철수", "대리"));
        employeeMap.put(2, new Employee("홍길동", "차장"));
        employeeMap.put(3, new Employee("박영희", "부장"));
    }

    @Override
    public void save(Employee employee) {
        employeeMap.put(employee.getEmpNum(), employee);
        log.info(employeeMap);
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();

        for (Integer key : employeeMap.keySet()) {
            employees.add(employeeMap.get(key));
        }
        return employees;
    }

    @Override
    public void remove(int empNum) {
        employeeMap.remove(empNum);
    }
}
