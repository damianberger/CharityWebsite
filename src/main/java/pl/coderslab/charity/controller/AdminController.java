package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.service.implementation.InstitutionServiceImpl;
import pl.coderslab.charity.service.implementation.RoleServiceImpl;
import pl.coderslab.charity.service.implementation.UserServiceImpl;



@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserServiceImpl userService;
    private RoleServiceImpl roleService;
    private InstitutionServiceImpl institutionService;

    public AdminController(UserServiceImpl userService, RoleServiceImpl roleService, InstitutionServiceImpl institutionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.institutionService = institutionService;
    }

    @GetMapping("/menu")
    public String loginAction(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        if(currentUser != null) {
            model.addAttribute("principal", currentUser.getUser());
        }
        return "admin/admin-menu";
    }

    @GetMapping("/institution/list")
    public String institutionsList(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if(currentUser != null) {
            model.addAttribute("principal", userService.findCurrentUser(currentUser));
        }
        model.addAttribute("institutions", institutionService.findAll());
        return "admin/institutionList";
    }

    @GetMapping("/institution/add")
    public String institutionForm(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if(currentUser != null) {
            model.addAttribute("principal", userService.findCurrentUser(currentUser));
        }
        model.addAttribute("institution", new Institution());
        return "admin/institutionForm";
    }

    @PostMapping("/institution/add")
    public String createInstitution(@ModelAttribute Institution institution, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/institutionForm";
        }
        institutionService.save(institution);
        return "redirect:/admin/institution/list";
    }

    @GetMapping("/institution/edit/{id}")
    public String institutionEditForm(@AuthenticationPrincipal CurrentUser currentUser, Model model, @PathVariable Long id) {
        if(currentUser != null) {
            model.addAttribute("principal", userService.findCurrentUser(currentUser));
        }
        model.addAttribute("institution", institutionService.findById(id));
        return "admin/institutionEdit";
    }

    @PostMapping("/institution/edit/{id}")
    public String editInstitution(@ModelAttribute Institution institution) {
        institutionService.edit(institution);
        return "redirect:/admin/institution/list";
    }


    @GetMapping("/institution/delete/{id}")
    public String delete(@AuthenticationPrincipal CurrentUser currentUser, @PathVariable long id, Model model) {
        if(currentUser != null) {
            model.addAttribute("principal", userService.findCurrentUser(currentUser));
        }
        institutionService.deleteInstitution(id);
        return "redirect:/admin/institution/list";
    }


}
