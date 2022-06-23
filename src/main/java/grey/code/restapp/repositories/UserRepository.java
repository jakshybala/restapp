package grey.code.restapp.repositories;

import grey.code.restapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
grey.code.restapp.repositories
Tarih: 23.06.2022, Saat: 12:43, Author: Grey 
*/
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
