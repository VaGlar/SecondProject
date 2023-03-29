package gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories;

import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByEmailAndPassword(String email,String password);

    User findByEmail(String email);
}
