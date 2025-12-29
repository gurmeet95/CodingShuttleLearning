package com.codingshuttle.learning.contollers;


import com.codingshuttle.learning.dto.EmployeeDTO;
import com.codingshuttle.learning.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
   private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
       this.employeeService=employeeService;
    }
    //Not good practice repository need to contact with service layer not Controller directly.
    //Not entity also can not be present in controller class.

    @GetMapping("/{employeeID}")
    public EmployeeDTO getEmployeeByID(@PathVariable(name ="employeeId")  Long id){

         return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required =false,name="inputAge") Integer age,
                                  @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }



    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
       return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping String updateEmpById(){
        return "Hello from PUt";
    }



}
