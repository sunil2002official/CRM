package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("customers", service.listAll());
        return "customer";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "form";
    }

    @PostMapping
    public String save(@ModelAttribute Customer customer) {
        service.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("customer", service.get(id));
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/customers";
    }
}
