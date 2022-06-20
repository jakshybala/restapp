package grey.code.restapp.controller;

import grey.code.restapp.model.Company;
import grey.code.restapp.services.CompanyService;
import grey.code.restapp.util.erros.CompanyError;
import grey.code.restapp.util.erros.CompanyNotCreatedException;
import grey.code.restapp.util.erros.CompanyNotFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
grey.code.restapp.controller
Tarih: 19.06.2022, Saat: 12:54, Author: Grey 
*/
@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;


    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    //allcompany
    @GetMapping()
    public List<Company> getAll() {
        return companyService.showAll();

    }
    //getByid
    @GetMapping("/{id}")
    public Company getById (@PathVariable("id") int id) {
        return companyService.getById(id);

    }
    @ExceptionHandler
    private ResponseEntity<CompanyError> handlerException(CompanyNotFoundExeption exeption) {
        CompanyError response = new CompanyError(
                "Company with this id not founded", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //create
    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid Company newCompany,
                                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> fieldError = bindingResult.getFieldErrors();
            for (FieldError error : fieldError) {
                errorMessage.append(error.getField()).append(" -- ").append(error.getDefaultMessage()).
                        append(";");
            }
            throw new CompanyNotCreatedException(errorMessage.toString());

        }
        companyService.saveCompany(newCompany);
        return ResponseEntity.ok(HttpStatus.OK);


    }
    @ExceptionHandler
    private ResponseEntity<CompanyError> handleException(CompanyNotCreatedException exception) {
        CompanyError response = new CompanyError(
                exception.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
