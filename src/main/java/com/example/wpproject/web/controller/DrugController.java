package com.example.wpproject.web.controller;

import com.example.wpproject.model.Drug;
import com.example.wpproject.model.Publisher;
import com.example.wpproject.model.Type;
import com.example.wpproject.service.PublisherService;
import com.example.wpproject.service.DrugService;
import com.example.wpproject.service.TypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/drugs")
public class DrugController {
    private final DrugService drugService;
    private final PublisherService publisherService;
    private final TypeService typeService;

    public DrugController(DrugService drugService, PublisherService publisherService, TypeService typeService) {
        this.drugService = drugService;
        this.publisherService = publisherService;
        this.typeService = typeService;
    }
    @GetMapping
    public String getDrugPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Drug> drugs = this.drugService.findAll();
        model.addAttribute("drugs", drugs);
        model.addAttribute("bodyContent", "drugs");
        return "master-template";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteDrug(@PathVariable Long id) {
        this.drugService.deleteById(id);
        return "redirect:/drugs";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit-form/{id}")
    public String editDrugPage(@PathVariable Long id, Model model) {
        if (this.drugService.findById(id).isPresent()) {
            Drug drug = this.drugService.findById(id).get();
            List<Publisher> publishers = this.publisherService.findAll();
            List<Type> types = this.typeService.findAll();
            model.addAttribute("publishers", publishers);
            model.addAttribute("types", types);
            model.addAttribute("drug", drug);
            model.addAttribute("bodyContent", "add-drug");
            return "master-template";
        }
        return "redirect:/products?error=DrugNotFound";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/add-form")
    public String addDrugPage(Model model) {
        List<Publisher> publishers = this.publisherService.findAll();
        List<Type> types = this.typeService.findAll();
        model.addAttribute("publishers", publishers);
        model.addAttribute("types", types);
        model.addAttribute("bodyContent", "add-drug");
        return "master-template";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Long publisher,
            @RequestParam String description,
            @RequestParam Long type,
            @RequestParam Double price) {
        if (id != null) {
            this.drugService.edit(id, name, publisher, description, type, price);
        } else {
            this.drugService.save(name, publisher, description, type, price);
        }
        return "redirect:/drugs";
    }






}
