package grey.code.restapp.dto;

import grey.code.restapp.model.Course;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/*
grey.code.restapp.dto
Tarih: 22.06.2022, Saat: 17:56, Author: Grey 
*/
@Getter
@Setter
public class TeacherDtoRequest {

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 34, message = "Name contain 3 or more letter!")
    private String lastName;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Write email address like exaple@mail.ru")
    private String email;

    private Course course;
}
