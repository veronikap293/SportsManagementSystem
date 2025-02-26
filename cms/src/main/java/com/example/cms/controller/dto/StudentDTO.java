package com.example.cms.controller.dto;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
}