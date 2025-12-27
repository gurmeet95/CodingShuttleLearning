package com.codingshuttle.learning.contollers;


import com.codingshuttle.learning.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
//    @GetMapping("/getMessage")
//    public String getSecretMessage(){
//        return "Hello Mr. Gurmeet Chahal";
//    }

    @GetMapping("/{employeeID}")
    //@PathVariable name must be same as Mapping name (@PathVariable  Long employeeID) or
    public EmployeeDTO getEmployeeByID(@PathVariable(name ="employeeId")  Long id){

         return new EmployeeDTO(id,"Anuj","abc@gmail.com",28, LocalDate.of(2024,2,12),true);
    }

     //@GetMapping(path="/employees")
     //This is fine but using RequestMapping("/employees") is good to use parent path.
    //After that @GetMapping(
    @RequestMapping // not good way to use @RequestMapping here
    public String getAllEmployees(@RequestParam(required =false,name="inputAge") Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "Hi age "+age+" "+sortBy;
    }

//    @PostMapping
//    //it also works with "/employees" only but in browser only get mapping is worked.
//    // so we only able to check get mapping only for post we need postman etc.
//    public String createNewEmployee(){
//        return "Hello From Post";
//    }

    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        //Here @RequestBody is used to bind the http request body to a Java object.
        inputEmployee.setId(100L);
        return inputEmployee;
    }

    @PutMapping String updateEmpById(){
        return "Hello from PUt";
    }



}
