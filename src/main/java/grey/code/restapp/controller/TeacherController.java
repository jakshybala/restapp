package grey.code.restapp.controller;

import grey.code.restapp.dto.CompanyDtoRequest;
import grey.code.restapp.dto.TeacherDtoRequest;
import grey.code.restapp.model.Company;
import grey.code.restapp.model.Teacher;
import grey.code.restapp.services.TeacherService;
import grey.code.restapp.util.erros.CompanyError;
import grey.code.restapp.util.erros.CompanyNotCreatedException;
import grey.code.restapp.util.erros.CompanyNotFoundExeption;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final ModelMapper modelMapper;

    public TeacherController(TeacherService teacherService, ModelMapper modelMapper) {
        this.teacherService = teacherService;
        this.modelMapper = modelMapper;
    }


    //allc
    @GetMapping()
    public List<Teacher> getAll() {
        return teacherService.showAll();

    }


    //getByid
    @GetMapping("/{id}")
    public Teacher getById (@PathVariable("id") int id) {
        return teacherService.getById(id);

    }
    @ExceptionHandler
    private ResponseEntity<CompanyError> handlerException(CompanyNotFoundExeption exeption) {
        CompanyError response = new CompanyError(
                "Teacher with this id not founded", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //create
    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid TeacherDtoRequest newTeacherDto,
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
        teacherService.creteTeacher(convertToTeacher(newTeacherDto));
        return ResponseEntity.ok(HttpStatus.OK);

    }
    //update
    @PostMapping("/{id}/edit")
    private ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid TeacherDtoRequest teacherDto,
                                              BindingResult bindingResult) {
        teacherService.updateTeacher(id, convertToTeacher(teacherDto));
        return ResponseEntity.ok(HttpStatus.OK);

    }
    //delete
    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        teacherService.deletById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }



    private Teacher convertToTeacher(TeacherDtoRequest newTeacherDto) {
/*        ModelMapper modelMapper = new ModelMapper();*/
        return modelMapper.map(newTeacherDto, Teacher.class);
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
