package gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories;

import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
