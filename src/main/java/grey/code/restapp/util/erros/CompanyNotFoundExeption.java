package grey.code.restapp.util.erros;

/*
grey.code.restapp.util.erros
Tarih: 19.06.2022, Saat: 13:40, Author: Grey 
*/
public class CompanyNotFoundExeption extends RuntimeException {
    public CompanyNotFoundExeption() {
    }

    public CompanyNotFoundExeption(String message) {
        super(message);
    }
}
