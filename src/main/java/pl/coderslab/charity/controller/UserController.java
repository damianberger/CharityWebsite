package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.implementation.RoleServiceImpl;
import pl.coderslab.charity.service.implementation.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
    public String editProfile(@ModelAttribute("principal") User user, @AuthenticationPrincipal CurrentUser currentUser, HttpServletRequest request, HttpServletResponse response){
        userService.editUser(currentUser, user);
        CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        cookieClearingLogoutHandler.logout(request, response, null);
        securityContextLogoutHandler.logout(request, response, null);
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
    public String changePassword(@ModelAttribute User user, @AuthenticationPrincipal CurrentUser currentUser, HttpServletRequest request, HttpServletResponse response){
        userService.changePassword(currentUser, user);
        CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        cookieClearingLogoutHandler.logout(request, response, null);
        securityContextLogoutHandler.logout(request, response, null);
        return "redirect:/login";
    }

}