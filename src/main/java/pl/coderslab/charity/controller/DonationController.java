package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;


import java.util.List;

@RequestMapping("/form")
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

    @GetMapping("/")
    public String donateForm(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("donation", new Donation());
        if(currentUser != null) {
            model.addAttribute("principal", currentUser.getUser());
        }
        return "user/form";
    }

    @ModelAttribute("categories")
    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutionList() {
        return institutionRepository.findAll();
    }

    @PostMapping("/")
    public String postDonateForm(@ModelAttribute Donation donation) {
        donationRepository.save(donation);
        return "user/form-confirmation";
    }

    @GetMapping("/confirmation")
    public String donateFormConfirm(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        if(currentUser != null) {
            model.addAttribute("principal", currentUser.getUser());
        }
        return "user/form-confirmation";
    }
}
