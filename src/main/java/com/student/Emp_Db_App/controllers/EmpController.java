package com.student.Emp_Db_App.controllers;

import com.student.Emp_Db_App.models.Employee;
import com.student.Emp_Db_App.repositiories.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmpController {

    @Autowired
    EmpRepository empRepository;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/employees")
    public String todos(Model model) {
        model.addAttribute("employees", empRepository.findAll());
        return "employees";
    }

    @PostMapping("/employeeNew")
    public String add(@RequestParam String firstName, @RequestParam String lastName,
                      @RequestParam String gender, @RequestParam String job,
                      @RequestParam double salary,
                      String email, String phone, Model model) {
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setGender(gender);
        employee.setJob(job);
        employee.setSalary(salary);
        employee.setEmail(email);
        employee.setPhone(phone);
        empRepository.save(employee);
        model.addAttribute("employees", empRepository.findAll());
        return "redirect:/employees";
    }

    @PostMapping("/employeeDelete/{id}")
    public String delete(@PathVariable long id, Model model) {
        empRepository.deleteById(id);
        model.addAttribute("employees", empRepository.findAll());
        return "redirect:/employees";
    }

    @PostMapping("/employeeUpdate/{id}")
    public String update(@PathVariable long id,
//                         String firstName, String lastName,
//                         String gender,
//                         String job,
                         @RequestParam double salary,
//                         String email, String phone,
                         Model model) {
        Employee employee = empRepository.findById(id).get();
//        if(!firstName.isEmpty())
//            employee.setFirstName(firstName);
//        if(!lastName.isEmpty())
//            employee.setLastName(lastName);
//        if(!gender.isEmpty())
//            employee.setGender(gender);
//        if(!job.isEmpty())
//            employee.setJob(job);
//        if(!email.isEmpty())
//            employee.setEmail(email);
//        if(!phone.isEmpty())
//            employee.setPhone(phone);

        double prevSalary= employee.getSalary();
        employee.setSalary(prevSalary+salary);
//        if("Yes".equals(todo.getCompleted())) {
//            todo.setCompleted("No");
//        }
//        else {
//            todo.setCompleted("Yes");
//        }
        empRepository.save(employee);
        model.addAttribute("employees", empRepository.findAll());
        return "redirect:/employees";
    }
}