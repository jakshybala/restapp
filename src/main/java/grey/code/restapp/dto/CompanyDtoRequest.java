package grey.code.restapp.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/*
grey.code.restapp.dto
Tarih: 20.06.2022, Saat: 23:33, Author: Grey 
*/

@Getter
@Setter
public class CompanyDtoRequest {

    @NotEmpty(message = "Company name should not be empty")
    @Size(min = 3, max = 34, message = "Company must contain 3 or more letter!")
    private String name;

    @NotEmpty(message = "Country should not be empty")
    @Size(min = 3, max = 35, message = "Country must contain 3 or more letter!")
    private String country;
}
