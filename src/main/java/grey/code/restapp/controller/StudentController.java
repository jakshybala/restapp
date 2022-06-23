package grey.code.restapp.controller;

import grey.code.restapp.dto.StudentDtoResponse;
import grey.code.restapp.model.Student;
import grey.code.restapp.services.StudentService;
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
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final ModelMapper modelMapper;

    public StudentController(StudentService studentService, ModelMapper modelMapper) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }


    //all
    @GetMapping()
    public List<Student> getAll() {
        return studentService.showAll();

    }


    //getByid
    @GetMapping("/{id}")
    public Student getById (@PathVariable("id") int id) {
        return studentService.getById(id);

    }
    @ExceptionHandler
    private ResponseEntity<CompanyError> handlerException(CompanyNotFoundExeption exeption) {
        CompanyError response = new CompanyError(
                "Student with this id not founded", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //create
    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid StudentDtoResponse newStudentDto,
                                           BindingResult bindingResultCourse) {
        if(bindingResultCourse.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> fieldError = bindingResultCourse.getFieldErrors();
            for (FieldError error : fieldError) {
                errorMessage.append(error.getField()).append(" -- ").append(error.getDefaultMessage()).
                        append(";");
            }
            throw new CompanyNotCreatedException(errorMessage.toString());

        }
        studentService.save(converterToStudent(newStudentDto));
        return ResponseEntity.ok(HttpStatus.OK);

    }
    //update
    @PostMapping("/{id}/edit")
    private ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid StudentDtoResponse updateStudentDto,
                                              BindingResult bindingResult) {
        studentService.updateStudent(id, converterToStudent(updateStudentDto));
        return ResponseEntity.ok(HttpStatus.OK);

    }
    //delete
    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    //convert by jackson to from CourseDto to Course
    private Student converterToStudent(StudentDtoResponse newStudentDto) {
        return modelMapper.map(newStudentDto, Student.class);

    }

    @ExceptionHandler
    private ResponseEntity<CompanyError> handleException(CompanyNotCreatedException exception) {
        CompanyError response = new CompanyError(
                exception.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



}
