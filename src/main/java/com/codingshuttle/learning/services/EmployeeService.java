package com.codingshuttle.learning.services;

import com.codingshuttle.learning.dto.EmployeeDTO;
import com.codingshuttle.learning.entities.EmployeeEntity;
import com.codingshuttle.learning.repositpries.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;



import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository,ModelMapper modelMapper){
        this.employeeRepository=employeeRepository;
        this.modelMapper=modelMapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long id) {
//        Optional<EmployeeEntity> employeeEntity= employeeRepository.findById(id);
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDTO.class));
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeEntities= employeeRepository.findAll();
        return employeeEntities.stream().map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());


    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        //Service provide upper hand to  add other logic here.
        //like to check is user is admin.
        // log something
        //more operations

        EmployeeEntity toSaveEntity=modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity SavedemployeeEntity= employeeRepository.save(toSaveEntity);
        return modelMapper.map(SavedemployeeEntity,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeByID(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity=modelMapper.map(employeeDTO,EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity= employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }
    public boolean isEmployeeExistsById(Long employeeID){
        return employeeRepository.existsById(employeeID);
    }

    public boolean deleteEmployeeById(Long employeeID) {
        boolean exists= isEmployeeExistsById(employeeID);
        if(!exists) return false;
        employeeRepository.deleteById(employeeID);
        return true;
    }


    public EmployeeDTO updatePartialEmployeeById(Long employeeID, Map<String, Object> updates) {
        boolean exists= isEmployeeExistsById(employeeID);
        if(!exists) return null;
        EmployeeEntity employeeEntity= employeeRepository.findById(employeeID).orElse(null);
        updates.forEach((field,value)->{
            Field fieldToBeUpdated=ReflectionUtils.findField(EmployeeEntity.class,field);
            fieldToBeUpdated.setAccessible(true);
           if("dateOfJoining".equals(field) && value instanceof String ){
               value= LocalDate.parse((String)value);
           }

            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity,value);
        });
       return  modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }
}
