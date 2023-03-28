package gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories;

import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByType(String type);


}
