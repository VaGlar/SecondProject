package gr.kariera.MindTheCode.SecondProject.SecondProject.Services;

import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Role;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.User;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories.RoleRepository;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User createUser(User user){
        Optional<Role> roleUser = roleRepository.findByType("user");
        if(roleUser.isPresent()){
            user.setRole(roleUser.get());
        }
        return userRepository.save(user);
    }

    public Optional<User> findUserByCredentials(String email,String password){

        return userRepository.findByEmailAndPassword(email, password);
    }

}
