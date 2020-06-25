package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.security.Principal;


@Controller
public class HomeController {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;

    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
    }

    @RequestMapping("/")
    public String homeAction(Principal principal, Model model){
        model.addAttribute("donations",donationRepository.findAll().size());
        model.addAttribute("institutions",institutionRepository.findAll());
        model.addAttribute("bags",donationRepository.bagsQuantity().orElse(0));
        if(principal != null) {
            model.addAttribute("userEmail", principal.getName());
        }
        return "index";
    }

    @GetMapping("/login")
    public String login(Principal principal,Model model){
        if(principal != null) {
            model.addAttribute("username", principal.getName());
        }
        return "login";
    }
}
