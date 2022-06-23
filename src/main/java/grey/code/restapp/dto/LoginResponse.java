package grey.code.restapp.dto;

/*
grey.code.restapp.dto
Tarih: 23.06.2022, Saat: 12:33, Author: Grey 
*/

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class LoginResponse {

    private String jwtToken;
    private String messages;
    private Set<String> authorities;
}
