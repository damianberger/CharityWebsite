package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;


import java.security.Principal;
import java.util.List;

@Controller
public class DonationController {

    private CategoryRepository categoryRepository;
    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;


    public DonationController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;

    }

    @GetMapping("/form")
    public String donateForm(Principal principal, Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("donation", new Donation());
        if(principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "form";
    }

    @ModelAttribute("categories")
    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutionList() {
        return institutionRepository.findAll();
    }

    @PostMapping("/form")
    public String donateForm(@ModelAttribute Donation donation) {
        donationRepository.save(donation);
        return "redirect:/form-confirmation";
    }

    @GetMapping("/form-confirmation")
    public String donateFormConfirm(Principal principal, Model model) {
        if(principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "form-confirmation";
    }
}
