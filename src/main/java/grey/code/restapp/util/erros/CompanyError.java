package grey.code.restapp.util.erros;

import lombok.Getter;
import lombok.Setter;

/*
grey.code.restapp.util.erros
Tarih: 19.06.2022, Saat: 13:38, Author: Grey 
*/
@Getter
@Setter
public class CompanyError {
    public String message;
    public Long timestap;

    public CompanyError(String message, Long timestap) {
        this.message = message;
        this.timestap = timestap;
    }
}
