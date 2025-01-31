package org.iesvdm.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.iesvdm.model.Comercial;
import org.iesvdm.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/comerciales")
public class ComercialController {
    @Autowired
    private ComercialService comercialService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(Model model) {
        List<Comercial> listaComerciales = comercialService.listAll();
        model.addAttribute("listaComerciales", listaComerciales);

        return "comerciales/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String details(@PathVariable Integer id, Model model) {
        Comercial comercial = comercialService.find(id);
        model.addAttribute("comercial", comercial);

        return "comerciales/details";
    }

    @RequestMapping(value = "/crear", method = RequestMethod.GET)
    public String create(Model model) {
        Comercial comercial = new Comercial();
        model.addAttribute("comercial", comercial);

        return "comerciales/create";
    }

    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    public String createSubmit(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("comercial", comercial);
            return "comerciales/create";
        }

        comercialService.create(comercial);

        return "redirect:/comerciales";
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Integer id, Model model) {
        Comercial comercial = comercialService.find(id);
        model.addAttribute("comercial", comercial);

        return "comerciales/edit";
    }

    @RequestMapping(value = "/editar/{id}", method = RequestMethod.POST)
    public String editSubmit(@Valid @ModelAttribute("comercial") Comercial comercial, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("comercial", comercial);
            return "comerciales/create";
        }

        comercialService.update(comercial);

        return "redirect:/comerciales";
    }

    @RequestMapping(value = "/borrar/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Integer id, Model model) {
        comercialService.delete(id);

        return "redirect:/comerciales";
    }
}
