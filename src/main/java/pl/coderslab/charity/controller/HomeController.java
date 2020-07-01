package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.Model.CurrentUser;
import pl.coderslab.charity.entity.Role;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.RoleServiceImpl;
import pl.coderslab.charity.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


@Controller
public class HomeController {
    private UserServiceImpl userService;
    private RoleServiceImpl roleService;
    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;

    public HomeController(UserServiceImpl userService, RoleServiceImpl roleService, InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @RequestMapping("/")
    public String homeAction(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        model.addAttribute("donations",donationRepository.findAll().size());
        model.addAttribute("institutions",institutionRepository.findAll());
        model.addAttribute("bags",donationRepository.bagsQuantity().orElse(0));
        if(currentUser != null) {
            model.addAttribute("principal", currentUser.getUser());
        }
        return "index";

    }

    @GetMapping("/login")
    public String loginAction(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        if(currentUser != null) {
            model.addAttribute("principal", currentUser.getUser());
        }
        return "login";
    }

    @GetMapping("/register")
    public String registerUser(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("user", new User());
        if(currentUser != null) {
            model.addAttribute("principal", currentUser.getUser());
        }
        return "register";
    }

    @PostMapping("/register")
    public String postRegisterUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        this.userService.saveUser(user);
        Set<Role> patientRoles = new HashSet<>();
        patientRoles.add(roleService.findOneByName("ROLE_USER"));
        return "redirect:/login";
    }
}
