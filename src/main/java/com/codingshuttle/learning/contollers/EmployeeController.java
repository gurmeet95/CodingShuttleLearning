package com.codingshuttle.learning.contollers;


import com.codingshuttle.learning.dto.EmployeeDTO;
import com.codingshuttle.learning.entities.EmployeeEntity;
import com.codingshuttle.learning.repositpries.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }
    //Not good practice repository need to contact with service layer not Controller directly.
    //Not entity also can not be present in controller class.

    @GetMapping("/{employeeID}")
    public EmployeeEntity getEmployeeByID(@PathVariable(name ="employeeId")  Long id){

         return employeeRepository.findById(id).orElse(null);
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees(@RequestParam(required =false,name="inputAge") Integer age,
                                  @RequestParam(required = false) String sortBy){
        return employeeRepository.findAll();
    }



    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
       return employeeRepository.save(inputEmployee);
    }

    @PutMapping String updateEmpById(){
        return "Hello from PUt";
    }



}
