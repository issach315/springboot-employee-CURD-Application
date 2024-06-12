package com.luv2code.springboot.tymeleafdemo.controller;

import com.luv2code.springboot.tymeleafdemo.entity.Employee;
import com.luv2code.springboot.tymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService theEmployeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        this.theEmployeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel){

        //get the employees from the db
        List<Employee> theEmployees = theEmployeeService.findAll();

        //add to the spring model
        theModel.addAttribute("employees",theEmployees);

        return "list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showForForAdd(Model theModel){

        //create  model attribute to bind the form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee",theEmployee);
        return "employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId")int theId , Model theModel){

        //get the employees from the service
        Employee theEmployee = theEmployeeService.findById(theId);

        //set employee oin the model to populate the frorm

        theModel.addAttribute("employee",theEmployee);

        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee")Employee theEmployee){

        //save the employee
        theEmployeeService.save(theEmployee);

        //use redirect prevent the  duplicate submission

        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId){
        //delete the employee
        theEmployeeService.deleteById(theId);

        //redirect to the employee list

        return "redirect:/employees/list";
    }




}
