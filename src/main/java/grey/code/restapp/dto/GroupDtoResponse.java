package grey.code.restapp.dto;

import grey.code.restapp.model.Course;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/*
grey.code.restapp.dto
Tarih: 21.06.2022, Saat: 19:04, Author: Grey 
*/
@Getter
@Setter
public class GroupDtoResponse {
    @NotEmpty(message = "Course should not be empty")
    @Size(min = 3, max = 34, message = "Course name must contain 3 or more letter!")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateEnd;

    private List<Course> courseList;


}
