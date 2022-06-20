package grey.code.restapp.repositories;

import grey.code.restapp.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;


/*
grey.crud.repositories
Tarih: 04.06.2022, Saat: 23:56, Author: Grey 
*/

public interface GroupRepo extends JpaRepository<Group, Integer> {

//    @Query("select g from Group g where Course.id = ?1")
//    List<Group> findAllByCourseyId(int courseId);
}
