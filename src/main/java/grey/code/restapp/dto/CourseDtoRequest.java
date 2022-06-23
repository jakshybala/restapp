package grey.code.restapp.dto;

import grey.code.restapp.model.Company;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/*
grey.code.restapp.dto
Tarih: 20.06.2022, Saat: 23:33, Author: Grey 
*/

@Getter
@Setter
public class CourseDtoRequest {

    @NotEmpty(message = "Course should not be empty")
    @Size(min = 3, max = 34, message = "Course name must contain 3 or more letter!")
    private String courseName;

    @Digits(fraction = 0, integer = 10, message ="Only digits!")
    private int duration;

    private Company company;
//    @ManyToOne
//    @JoinColumn(name = "company_id", referencedColumnName = "id")

    //    private Teacher teacher;

//    @ManyToMany(mappedBy = "courseList")
//    private List<Group> groupList;
}
