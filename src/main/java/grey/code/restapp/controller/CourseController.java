package grey.code.restapp.controller;

import grey.code.restapp.dto.CourseDtoRequest;
import grey.code.restapp.model.Course;
import grey.code.restapp.services.CourseService;
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
import java.util.ArrayList;
import java.util.List;

/*
grey.code.restapp.controller
Tarih: 19.06.2022, Saat: 12:54, Author: Grey 
*/
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;
    private final ModelMapper modelMapper;

    public CourseController(CourseService courseService, ModelMapper modelMapper) {
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }


    //allcompany
    @GetMapping()
    public List<Course> getAll() {
        return courseService.showAll();

    }


    //getByid
    @GetMapping("/{id}")
    public Course getById (@PathVariable("id") int id) {
        return courseService.getById(id);

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
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid CourseDtoRequest newCourse,
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
        courseService.saveCourse(converterToCourse(newCourse));
        return ResponseEntity.ok(HttpStatus.OK);

    }
    //update
    @PostMapping("/{id}/edit")
    private ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid CourseDtoRequest updateCourseDto,
                                              BindingResult bindingResult) {
        courseService.updateCourse(id, converterToCourse(updateCourseDto));
        return ResponseEntity.ok(HttpStatus.OK);

    }
    //delete
    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    //convert by jackson to from CourseDto to Course
    private Course converterToCourse(CourseDtoRequest newCourseDto) {
        return modelMapper.map(newCourseDto, Course.class);

    }

    @ExceptionHandler
    private ResponseEntity<CompanyError> handleException(CompanyNotCreatedException exception) {
        CompanyError response = new CompanyError(
                exception.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



}
