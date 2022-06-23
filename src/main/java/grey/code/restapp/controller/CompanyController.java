package grey.code.restapp.controller;

import grey.code.restapp.dto.CompanyDtoRequest;
import grey.code.restapp.dto.CompanyDtoResponse;
import grey.code.restapp.model.Company;
import grey.code.restapp.services.CompanyService;
import grey.code.restapp.util.erros.CompanyError;
import grey.code.restapp.util.erros.CompanyNotCreatedException;
import grey.code.restapp.util.erros.CompanyNotFoundExeption;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final ModelMapper modelMapper;

    public CompanyController(CompanyService companyService, ModelMapper modelMapper) {
        this.companyService = companyService;
        this.modelMapper = modelMapper;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
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
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid CompanyDtoRequest newCompanyDto,
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
        companyService.saveCompany(convertToCompany(newCompanyDto));
        return ResponseEntity.ok(HttpStatus.OK);

    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    //update
    @PostMapping("/{id}/edit")
    private ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid CompanyDtoRequest updateCompanyDto,
                                              BindingResult bindingResult) {
        companyService.updateCompany(id, convertToCompany(updateCompanyDto));
        return ResponseEntity.ok(HttpStatus.OK);

    }
    //delete
    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        companyService.deletCompany(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }



    private Company convertToCompany(CompanyDtoRequest newCompanyDto) {
/*        ModelMapper modelMapper = new ModelMapper();*/
        return modelMapper.map(newCompanyDto, Company.class);
//        Company company = new Company();
//        company.setName(newCompanyDto.getName());
//        company.setCountry(newCompanyDto.getCountry());
//        return company;

    }

    @ExceptionHandler
    private ResponseEntity<CompanyError> handleException(CompanyNotCreatedException exception) {
        CompanyError response = new CompanyError(
                exception.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



}
