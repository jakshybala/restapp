package grey.code.restapp.repositories;

import grey.code.restapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


/*
grey.crud.repositories
Tarih: 04.06.2022, Saat: 23:56, Author: Grey 
*/

public interface PersonRepo extends JpaRepository<Person, Integer> {


}
