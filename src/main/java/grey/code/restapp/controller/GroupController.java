package grey.code.restapp.controller;

import grey.code.restapp.dto.CourseDtoRequest;
import grey.code.restapp.dto.GroupDtoResponse;
import grey.code.restapp.model.Course;
import grey.code.restapp.model.Group;
import grey.code.restapp.services.GroupService;
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
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;
    private final ModelMapper modelMapper;

    public GroupController(GroupService groupService, ModelMapper modelMapper) {
        this.groupService = groupService;
        this.modelMapper = modelMapper;
    }


    //all
    @GetMapping()
    public List<Group> getAll() {
        return groupService.showAll();

    }


    //getByid
    @GetMapping("/{id}")
    public Group getById (@PathVariable("id") int id) {
        return groupService.getById(id);

    }
    @ExceptionHandler
    private ResponseEntity<CompanyError> handlerException(CompanyNotFoundExeption exeption) {
        CompanyError response = new CompanyError(
                "Group with this id not founded", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    //create
    @PostMapping
    public ResponseEntity<HttpStatus> save(@RequestBody @Valid GroupDtoResponse newGroupDto,
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

        groupService.createGroup(converterToCourse(newGroupDto));
        return ResponseEntity.ok(HttpStatus.OK);

    }
    //update
    @PostMapping("/{id}/edit")
    private ResponseEntity<HttpStatus> update(@PathVariable("id") int id, @RequestBody @Valid GroupDtoResponse updateGroupDto,
                                              BindingResult bindingResult) {
        groupService.updateGroup(id, converterToCourse(updateGroupDto));
        return ResponseEntity.ok(HttpStatus.OK);

    }
    //delete
    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> delete(@PathVariable("id") int id) {
        groupService.deletById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    //convert by jackson to from CourseDto to Course
    private Group converterToCourse(GroupDtoResponse newGroupDto) {
        return modelMapper.map(newGroupDto, Group.class);

    }

    @ExceptionHandler
    private ResponseEntity<CompanyError> handleException(CompanyNotCreatedException exception) {
        CompanyError response = new CompanyError(
                exception.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }



}
