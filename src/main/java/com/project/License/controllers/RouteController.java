package com.project.License.controllers;

import com.project.License.models.License;
import com.project.License.models.Person;
import com.project.License.services.LicenseService;
import com.project.License.services.PersonService;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
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
        model.addAttribute("persons", users);
        return "newLicense";
    }

    @PostMapping("/create/license")
    public String create(@Valid @ModelAttribute("license") License license, BindingResult result, Model model) {
        System.out.println(result);
        if (result.hasErrors()) {
            System.out.println("Hello");
            return "redirect:/license/new";
        } else {
            System.out.println("Hello23");
            License newlic = licenseService.addLicense(license);
            Long personId = newlic.getPerson().getId();

            return "redirect:/display/" + personId;
        }
    }

    @RequestMapping("/display/{id}")
    public String display(@PathVariable("id") Long id, Model model) {
        Person users = personService.getPerson(id);
        
        // List<Person> users = personService.getById(id);
        model.addAttribute("users", users);
        return "display";
    }


}