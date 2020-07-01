package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.Model.CurrentUser;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.RoleServiceImpl;
import pl.coderslab.charity.service.UserServiceImpl;



@Controller
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl userService;
    private RoleServiceImpl roleService;

    public UserController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    user data change

    @GetMapping("/user-edit")
    public String editProfile(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        if(currentUser != null) {
            model.addAttribute("principal", userService.findCurrentUser(currentUser));
        }
        return "/user/user-data";
    }

    @PostMapping("/user-edit")
    public String editProfile(@ModelAttribute("principal") User user, @AuthenticationPrincipal CurrentUser currentUser){
        userService.editUser(currentUser, user);
        return "redirect:/login";
    }

//    password change

    @GetMapping("/edit-pass")
    public String changePassword(@AuthenticationPrincipal CurrentUser currentUser,Model model){
        if(currentUser != null) {
            model.addAttribute("principal", userService.findCurrentUser(currentUser));
        }
        return "/user/user-password";
    }

    @PostMapping("/edit-pass")
    public String changePassword(@ModelAttribute User user, @AuthenticationPrincipal CurrentUser currentUser){
        userService.changePassword(currentUser, user);
        // security context - clear
        return "redirect:/login";
    }

}