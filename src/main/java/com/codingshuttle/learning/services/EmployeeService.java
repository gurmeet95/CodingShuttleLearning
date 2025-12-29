package com.codingshuttle.learning.services;

import com.codingshuttle.learning.dto.EmployeeDTO;
import com.codingshuttle.learning.entities.EmployeeEntity;
import com.codingshuttle.learning.repositpries.EmployeeRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository,ModelMapper modelMapper){
        this.employeeRepository=employeeRepository;
        this.modelMapper=modelMapper;
    }


    public EmployeeDTO getEmployeeById(Long id) {
        EmployeeEntity employeeEntity= employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
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
}
