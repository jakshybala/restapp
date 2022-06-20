package grey.code.restapp.repositories;


import grey.code.restapp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;


/*
grey.crud.repositories
Tarih: 04.06.2022, Saat: 23:56, Author: Grey 
*/

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {

//    @Query("select c from Course c where c.company.id = ?1")
//    List<Course> findAllByCompanyId(int companyId);
}
