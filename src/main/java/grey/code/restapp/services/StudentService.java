package grey.code.restapp.services;

import grey.code.restapp.model.Course;
import grey.code.restapp.model.Student;
import grey.code.restapp.repositories.StudentRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/*
grey.crud.services
Tarih: 05.06.2022, Saat: 0:00, Author: Grey 
*/
@Service
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepo studentRepo;

    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    public List<Student> showAll() {
        return studentRepo.findAll();
    }

    public Student getById(int id) {
        Optional<Student> findById = studentRepo.findById(id);
        return findById.orElse(null);

    }
    @Transactional
    public void save(Student newStudent) {
        studentRepo.save(newStudent);
    }

    @Transactional
    public void updateStudent(int id, Student updateStudent) {
        updateStudent.setId(id);
        studentRepo.save(updateStudent);

    }
    @Transactional
    public void deleteStudent(int id) {
        studentRepo.deleteById(id);
    }


}
