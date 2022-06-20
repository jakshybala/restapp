package grey.code.restapp.util.erros;

/*
grey.code.restapp.util.erros
Tarih: 19.06.2022, Saat: 16:18, Author: Grey 
*/
public class CompanyNotCreatedException extends RuntimeException{
    public CompanyNotCreatedException() {
    }

    public CompanyNotCreatedException(String message) {
        super(message);
    }
}
