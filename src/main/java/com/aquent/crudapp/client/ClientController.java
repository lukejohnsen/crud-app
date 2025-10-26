package com.aquent.crudapp.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // returns current list of clients
    @GetMapping(value = "list")
    public ModelAndView list(@RequestParam(value = "letter", required = false) String letter) {
        ModelAndView mav = new ModelAndView("client/list");
        if (letter != null && !letter.isEmpty()) {
            mav.addObject("clients", clientService.findByCompanyNameStartingWith(letter));
            mav.addObject("selectedLetter", letter.toUpperCase());
        } else {
            mav.addObject("clients", clientService.findAll());
        }
        return mav;
    }

    // returns fresh form to create new client
    @GetMapping(value = "create")
    public ModelAndView create() {
        ModelAndView mav = new ModelAndView("client/create");
        mav.addObject("client", new Client());
        mav.addObject("errors", new ArrayList<String>());
        return mav;
    }

    // posts new client to database or returns validation errors
    @PostMapping(value = "create")
    public ModelAndView create(Client client) {
        List<String> errors = clientService.validateClient(client);
        if (errors.isEmpty()) {
            clientService.createClient(client);
            return new ModelAndView("redirect:/client/list");
        } else {
            ModelAndView mav = new ModelAndView("client/create");
            mav.addObject("client", client);
            mav.addObject("errors", errors);
            return mav;
        }
    }

    // returns read-only view of client
    @GetMapping(value = "view/{clientId}")
    public ModelAndView view(@PathVariable("clientId") Integer clientId) {
        Client client = clientService.findClientById(clientId);
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }
        ModelAndView mav = new ModelAndView("client/view");
        mav.addObject("client", client);
        return mav;
    }

    // returns form to edit client
    @GetMapping(value = "edit/{clientId}")
    public ModelAndView edit(@PathVariable("clientId") Integer clientId) {
        Client client = clientService.findClientById(clientId);
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }
        ModelAndView mav = new ModelAndView("client/edit");
        mav.addObject("client", client);
        mav.addObject("errors", new ArrayList<String>());
        return mav;
    }

    // posts edited client info to database or returns validation errors
    @PostMapping(value = "edit")
    public ModelAndView edit(Client client) {
        List<String> errors = clientService.validateClient(client);
        if (errors.isEmpty()) {
            clientService.updateClient(client);
            return new ModelAndView("redirect:/client/list");
        } else {
            ModelAndView mav = new ModelAndView("client/edit");
            mav.addObject("client", client);
            mav.addObject("errors", errors);
            return mav;
        }
    }

    public static final String COMMAND_DELETE = "Delete";

    // gets delete confirmation page
    @GetMapping(value = "delete/{clientId}")
    public ModelAndView delete(@PathVariable("clientId") Integer clientId) {
        Client client = clientService.findClientById(clientId);
        if (client == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found");
        }
        ModelAndView mav = new ModelAndView("client/delete");
        mav.addObject("client", client);
        return mav;
    }

    // handles delete confirm or cancel, redirects to client list
    @PostMapping(value = "delete")
    public String delete(@RequestParam("command") String command, @RequestParam("clientId") Integer clientId) {
        if (COMMAND_DELETE.equals(command)) {
            clientService.deleteClient(clientId);
        }
        return "redirect:/client/list";
    }
}
