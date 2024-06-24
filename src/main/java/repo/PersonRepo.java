package repo;

import POJO.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person, Long> {


    Optional<Person> findByEmail(String email);
}
