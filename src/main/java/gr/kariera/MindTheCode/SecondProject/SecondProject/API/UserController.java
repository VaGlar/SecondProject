package gr.kariera.MindTheCode.SecondProject.SecondProject.API;

import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.User;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser (@RequestBody User user){
        userService.createUser(user);
    }
}
