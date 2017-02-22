/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.arek.zadanko.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.arek.zadanko.services.CustomerService;

/**
 *
 * @author Arkadiusz Gibes <arkadiusz.gibes@yahoo.com>
 */
@Controller
public class GeneratorController {

    @Autowired
    CustomerService klientService;

    @RequestMapping(value = "/generator", method = RequestMethod.GET)
    public String generator(Model model, @RequestParam(name = "customersCount", defaultValue = "10") int count) {

        try {
            klientService.createCustomers(count);
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "generator";
    }
}
