package grey.code.restapp.dto;

import grey.code.restapp.model.Group;
import grey.code.restapp.model.StudyFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/*
grey.code.restapp.dto
Tarih: 21.06.2022, Saat: 6:07, Author: Grey 
*/

@Setter
@Getter
public class StudentDtoResponse {
    @NotEmpty(message = "Student name should not be empty")
    @Size(min = 3, max = 34, message = "Name must contain 3 or more letter!")
    private String name;

    @NotEmpty(message = "Student name should not be empty")
    @Size(min = 3, max = 34, message = "Name must contain 3 or more letter!")
    private String lastName;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Write email address like exaple@mail.ru")
    private String email;

    @Enumerated(EnumType.ORDINAL)
    private StudyFormat studyFormat;


    private Group group;



}
