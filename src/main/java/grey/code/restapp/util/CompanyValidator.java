package grey.code.restapp.util;

import grey.code.restapp.model.Company;
import grey.code.restapp.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/*
grey.crud.util
Tarih: 31.05.2022, Saat: 1:27, Author: Grey 
*/@Component
public class CompanyValidator implements Validator {
    private final CompanyService companyService;
    @Autowired
    public CompanyValidator(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Company.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }


//    @Override
//    public void validate(Object target, Errors errors) {
//        Person person = (Person) target;
//
//        if(personDao.showEmail(person.getEmail()).isPresent()) {
//            errors.rejectValue("email", "", "This email already token");
//
//        }


    }
