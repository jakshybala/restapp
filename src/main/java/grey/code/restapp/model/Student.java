package grey.code.restapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/*
grey.code.restapp.model
Tarih: 22.06.2022, Saat: 20:02, Author: Grey 
*/
@Entity
@Table(name = "Student")
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Company name should not be empty")
    @Size(min = 3, max = 34, message = "Name must contain 3 or more letter!")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "Company name should not be empty")
    @Size(min = 3, max = 34, message = "Name must contain 3 or more letter!")
    private String lastName;

    @Column(name = "email")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Write email address like exaple@mail.ru")
    private String email;
    @Enumerated(EnumType.ORDINAL)
    private StudyFormat studyFormat;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;


}
