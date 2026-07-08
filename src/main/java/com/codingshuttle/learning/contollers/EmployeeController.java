package com.codingshuttle.learning.contollers;


import com.codingshuttle.learning.dto.EmployeeDTO;
import com.codingshuttle.learning.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable(name ="employeeID")  Long id){

        Optional<EmployeeDTO> employeeDTO= employeeService.getEmployeeById(id);
        return  employeeDTO
                .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required =false,name="inputAge") Integer age,
                                  @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }



    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO inputEmployee){
       EmployeeDTO savedEmployee=employeeService.createNewEmployee(inputEmployee);
       return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path="/{employeeID}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody @Valid  EmployeeDTO employeeDTO,@PathVariable Long employeeID){
        return ResponseEntity.ok(employeeService.updateEmployeeByID(employeeID,employeeDTO));
        //If with @PutMapping we try to update name only,other data will set to null itself.
    }

    @DeleteMapping("/{employeeID}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeID){
        boolean gotDeleted=employeeService.deleteEmployeeById(employeeID);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{employeeID}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String,Object>updates,
                                                 @PathVariable Long employeeID){
        EmployeeDTO employeeDTO= employeeService.updatePartialEmployeeById(employeeID,updates);
        if(employeeDTO==null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(employeeDTO);
    }
}




