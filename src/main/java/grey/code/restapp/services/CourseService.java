package grey.code.restapp.services;

import grey.code.restapp.model.Course;
import grey.code.restapp.repositories.CourseRepo;
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
public class CourseService {

    private final CourseRepo courseRepo;

    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> showAll() {
        return courseRepo.findAll();
    }

    public List<Course> findAllByCompanyId(int companyId) {
        return courseRepo.findAllByCompanyId(companyId);
    }
    public Course getById(int id) {
        Optional<Course> findById = courseRepo.findById(id);
        return findById.orElse(null);

    }
    @Transactional
    public void saveCourse(Course newCourse) {
        courseRepo.save(newCourse);
    }

    @Transactional
    public void updateCourse(int id, Course updateCourse) {
        updateCourse.setId(id);
        courseRepo.save(updateCourse);

    }
    @Transactional
    public void deleteCourse(int id) {
        courseRepo.deleteById(id);
    }


}
