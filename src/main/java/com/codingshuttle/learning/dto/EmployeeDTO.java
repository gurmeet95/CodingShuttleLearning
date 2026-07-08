package com.codingshuttle.learning.dto;





import com.codingshuttle.learning.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

//Basically this is POJO class.

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message="Name of the Employee can not be Blank")
    @Size(min = 3,max = 10,message ="Number of character must be in ranges 3-10")
    private String name;

    @Email(message = "Email should be valid email.")
    private String email;

    @Max(value = 80,message = "Age can not be greater than 80")
    @Min(value = 18,message = "Age of employee can not be less than 18")
    private Integer age;


    @NotBlank(message="Role of the Employee can not be Blank")
    //@Pattern(regexp = "^(ADMIN|USER)$",message = "Role of employee can be USER or ADMIN")
    @EmployeeRoleValidation
    private String role; //Admin,User

    @PastOrPresent(message = "DateofJoining can not be in future")
    private LocalDate dateOfJoining;

    private Boolean active;

    @NotNull(message = "Salary can not be null")@Positive(message = "Salary can not be negative")
    @Digits(integer = 6,fraction = 2,message = "Salary can be in the form xxxxxx.yy")
    @DecimalMax(value = "200000.99")
    @DecimalMin(value="100.50")
    private Double salary;
}
