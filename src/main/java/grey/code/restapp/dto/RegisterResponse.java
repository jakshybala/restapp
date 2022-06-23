package grey.code.restapp.dto;

/*
grey.code.restapp.dto
Tarih: 23.06.2022, Saat: 12:39, Author: Grey 
*/

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class RegisterResponse {

    private String id;
    private String email;
    private String firstName;
}
