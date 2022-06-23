package grey.code.restapp.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/*
grey.code.restapp.dto
Tarih: 21.06.2022, Saat: 6:07, Author: Grey 
*/

@Setter
@Getter
public class CompanyDtoResponse {

    @NotEmpty(message = "Company name should not be empty")
    @Size(min = 3, max = 34, message = "Name must contain 3 or more letter!")
    private String name;

    @NotEmpty(message = "Country should not be empty")
    @Size(min = 3, max = 35, message = "Name must contain 3 or more letter!")
    private String country;


    private LocalDateTime createdAt;


    private LocalDateTime updateAt;


    @Setter
    @Getter
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class RegisterRequest {

        private String email;
        private String password;
        private String firstName;
    }
}
