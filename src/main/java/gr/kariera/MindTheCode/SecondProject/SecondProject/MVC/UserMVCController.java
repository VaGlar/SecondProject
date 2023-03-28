package gr.kariera.MindTheCode.SecondProject.SecondProject.MVC;

import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.UserCredentials;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Services.UserService;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserMVCController {

    private final UserService userService;

    public UserMVCController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserCredentials userCredentials, Model model){
        userService.findUserByCredentials(userCredentials.getEmail(),userCredentials.getPassword());
        //na tsekarei na vrei ton xristi .... kai na pernaei sto model tis prirofories tou xristi
        // vazw  sto model ta stoixeia tou xristi  kai ton stelnw se opoia selida 8elw .
        //alliws vazo sto modelo oti pige lathos ( san error message ) model.setAttribute ("errormesssage","fail to login") kai to nstelnw sto register..
        return "redirect:/login";
    }




}
