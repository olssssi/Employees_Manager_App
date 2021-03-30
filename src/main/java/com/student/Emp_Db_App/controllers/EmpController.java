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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Controller
public class EmpController {

    @Autowired
    EmpRepository empRepository;

    @GetMapping
    public String index() {
        return "index";
    }

    public EmpController(EmpRepository empRepository) {
        this.empRepository = empRepository;
        initialize();
    }

    public void initialize(){
        Employee emp1=new Employee("Sam","Smith","Male","Manager",5000.00,"s.smith@mail.com","500200300");
        Employee emp2=new Employee("Lisa","Parker","Female","Boss",10000.00,"l.parker@mail.com","500300600");
        Employee emp3=new Employee("Sarah","Perry","Female","Senior Java Developer",8000.00,"s.perry@mail.com","500900800");
        Employee emp4=new Employee("Matthew","Newton","Male","Data Anylyst",5000.00,"m.newton@mail.com","520620320");
        Employee emp5=new Employee("Isaac","Johns","Male","Marketing Specialist",4000.00,"i.johns@mail.com","500400300");
        Employee emp6=new Employee("Anne","Williams","Female","Secretary",3500.00,"a.williams@mail.com","560690720");
        Employee emp7=new Employee("Ross","Davis","Male","Test Engineer",4000.00,"r.davis@mail.com","500420102");
        empRepository.save(emp1);
        empRepository.save(emp2);
        empRepository.save(emp3);
        empRepository.save(emp4);
        empRepository.save(emp5);
        empRepository.save(emp6);
        empRepository.save(emp7);
    }

    @GetMapping("/employees")
    public String employees(Model model) {
        model.addAttribute("employees", empRepository.findAll());
        return "employees";
    }

    @PostMapping("/employeeNew")
    public String add(@RequestParam @Size(min = 1) String firstName, @RequestParam @Size(min = 1) String lastName,
                      @RequestParam String gender, @RequestParam String job,
                      @RequestParam  double salary,
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