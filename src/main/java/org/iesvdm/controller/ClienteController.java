package org.iesvdm.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.iesvdm.model.Cliente;
import org.iesvdm.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(Model model) {
		List<Cliente> listaClientes =  clienteService.listAll();
		model.addAttribute("listaClientes", listaClientes);
				
		return "clientes/index";
	}

	@RequestMapping(value = "/crear", method = RequestMethod.GET)
	public String create(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("cliente", cliente);

		return "clientes/create";
	}

	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public String createSubmit(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("cliente", cliente);
			return "clientes/create";
		}

		clienteService.create(cliente);

		return "redirect:/clientes";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String details(@PathVariable Integer id, Model model) {
		Cliente cliente = clienteService.find(id);
		model.addAttribute("cliente", cliente);

		return "clientes/details";
	}

	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id, Model model) {
		Cliente cliente = clienteService.find(id);
		model.addAttribute("cliente", cliente);

		return "clientes/edit";
	}

	@RequestMapping(value = "/editar/{id}", method = RequestMethod.POST)
	public String editSubmit(@PathVariable Integer id, @Valid @ModelAttribute("cliente") Cliente cliente, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("cliente", cliente);
			return "clientes/edit";
		}

		clienteService.update(cliente);

		return "redirect:/clientes";
	}

	@RequestMapping(value = "/borrar/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable Integer id) {
		clienteService.delete(id);

		return "redirect:/clientes";
	}
}
