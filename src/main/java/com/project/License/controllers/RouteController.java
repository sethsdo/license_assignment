package com.project.License.controllers;

import com.project.License.models.License;
import com.project.License.models.Person;
import com.project.License.services.LicenseService;
import com.project.License.services.PersonService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouteController {
    private final LicenseService licenseService;
    private final PersonService personService;

    public RouteController(LicenseService licenseService, PersonService personService) {
        this.licenseService = licenseService;
        this.personService = personService;
    }

    //main route
    @RequestMapping("/")
    public String index(@ModelAttribute("person") Person person, Model model) {
        return "index";
    }

    @PostMapping("create")
    public String create(@Valid @ModelAttribute("person") Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "index";
        } else {
            personService.addPerson(person);
            return "redirect:/license/new";
        }
    }

    @RequestMapping("/license/new")
    public String license(@ModelAttribute("license") License license, Model model) {
        List<Person> users = personService.allPersons();
        model.addAttribute("users", users);
        return "newLicense";
    }

    @PostMapping("/create/license")
    public String create(@Valid @ModelAttribute("license") License license, BindingResult result, Model model) {
        System.out.println("Hello");
        if (result.hasErrors()) {
            return "redirect:/license/new";
        } else {
            licenseService.addLicense(license);
            return "redirect:/display";
        }
    }

    @RequestMapping("/display")
    public String display(@ModelAttribute("person") Person person, Model model) {
        List<Person> users = personService.allPersons();
        model.addAttribute("users", users);
        
        return "display";
    }


}