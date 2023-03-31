package gr.kariera.MindTheCode.SecondProject.SecondProject.MVC;

import gr.kariera.MindTheCode.SecondProject.SecondProject.DTOs.UserCredentials;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.Address;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Entities.User;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Repositories.UserRepository;
import gr.kariera.MindTheCode.SecondProject.SecondProject.Services.UserService;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class UserMVCController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserMVCController(UserService userService,
                             UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserCredentials userCredentials, Model model) throws Exception {
       Optional<User> currentUser= userService.findUserByCredentials(userCredentials.getEmail(),userCredentials.getPassword());
       if(currentUser.equals(userRepository)){
           model.addAttribute(currentUser);
       return "order-table";}
       else
           throw  new Exception("User not found");
        //na tsekarei na vrei ton xristi .... kai na pernaei sto model tis prirofories tou xristi
        // vazw  sto model ta stoixeia tou xristi  kai ton stelnw se opoia selida 8elw .
        //alliws vazo sto modelo oti pige lathos ( san error message ) model.setAttribute ("errormesssage","fail to login") kai to nstelnw sto register..

    }

    @RequestMapping("/my-profile")
    public String myProfile(Model model,User user) {
        model.addAttribute("user", user);
        return "myAddress";
    }
    @RequestMapping(value="/new-user", method=RequestMethod.POST)
    public String newUserPost(@ModelAttribute("user") User user, BindingResult bindingResults,
                              RedirectAttributes redirectAttributes, Model model) {
        model.addAttribute("email", user.getEmail());
        model.addAttribute("username", user.getEmail());

        boolean invalidFields = false;
        if (bindingResults.hasErrors()) {
            return "redirect:/login";
        }
        if (userService.findByEmail(user.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("emailExists", true);
            invalidFields = true;
        }
        if (invalidFields) {
            return "redirect:/login";
        }
        user = userService.createUser(user);
        return "redirect:/products/index";
    }

    @RequestMapping(value="/update-user-info", method=RequestMethod.POST)
    public String updateUserInfo( @ModelAttribute("user") User user,
                                  @RequestParam("newPassword") String newPassword,
                                  Model model, Principal principal) throws Exception {
        User currentUser = userService.findByEmail(user.getEmail());
        if(currentUser == null) {
            throw new Exception ("User not found");
        }
        /*check username already exists*/
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null && !existingUser.getId().equals(currentUser.getId()))  {
            model.addAttribute("usernameExists", true);
            return "myProfile";
        }
        /*check email already exists*/
        existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null && !existingUser.getId().equals(currentUser.getId()))  {
            model.addAttribute("emailExists", true);
            return "myProfile";
        }
        /*update password*/
        if (newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")){
            String dbPassword = currentUser.getPassword();
            if((user.getPassword().equals(dbPassword) )){
                currentUser.setPassword((newPassword));
            } else {
                model.addAttribute("incorrectPassword", true);
                return "myProfile";
            }
        }
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());
        userService.save(currentUser);
        model.addAttribute("updateSuccess", true);
        model.addAttribute("user", currentUser);
        return "myProfile";
    }
    @RequestMapping("/my-address")
    public String myAddress(Model model, Principal principal) {
        User user = userService.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "myAddress";
    }

    @RequestMapping(value="/update-user-address", method=RequestMethod.POST)
    public String updateUserAddress(@ModelAttribute("user") User user, @ModelAttribute("address") Address address,
                                    Model model ) throws Exception {
        User currentUser = userService.findByEmail(user.getEmail());
//        if(currentUser == null) {
//            throw new Exception ("User not found");
//        }
        currentUser.setAddress(address);
        userService.save(currentUser);
        return "redirect:/my-address";
    }





}
