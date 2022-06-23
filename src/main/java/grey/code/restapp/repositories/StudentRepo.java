package grey.code.restapp.repositories;

import grey.code.restapp.model.Course;
import grey.code.restapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/*
grey.crud.repositories
Tarih: 04.06.2022, Saat: 23:56, Author: Grey 
*/

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
