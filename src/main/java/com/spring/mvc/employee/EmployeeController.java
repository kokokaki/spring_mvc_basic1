package com.spring.mvc.employee;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@Log4j2  //로그를 만들어주는 기능
public class EmployeeController {

    //저장소 역할에게 처리를 위임하기 위해 Repository를 Composition
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //사원 등록 화면을 열어주는 요청
    @GetMapping("/emp/form")
    public String form() {
        log.info("/emp/form GET 요청 발생!");
        return "employee/emp-form";
    }

    //사원 등록 프로세스 요청
    @PostMapping("/emp/register")
    public String register(Employee employee) {
        log.info("/emp/register POST - " + employee);
        //저장 명령
        employeeRepository.save(employee);
        return "redirect:/emp/list"; // 요청이 끝난 후 /emp/list로 재요청
    }

    //사원 전체 조회 요청
    @GetMapping("/emp/list")
    public String list(Model model) {
        log.info("/emp/list GET !");
        List<Employee> employeeList = employeeRepository.findAll();
        model.addAttribute("empList", employeeList);
        return "employee/emp-form";
    }

    //삭제 요청
    @GetMapping("/emp/delete")
    public String delete(int empNum) {
        log.info("/emp/delete GET !");
        employeeRepository.remove(empNum);
        return "redirect:/emp/list";
    }
}
