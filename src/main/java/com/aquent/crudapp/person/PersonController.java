package com.aquent.crudapp.person;
import com.aquent.crudapp.client.ClientService;

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

/**
 * Controller for handling basic person management operations.
 */
@Controller
@RequestMapping("person")
public class PersonController {

    public static final String COMMAND_DELETE = "Delete";

    private final PersonService personService;
    private final ClientService clientService;

    public PersonController(PersonService personService, ClientService clientService) {
        this.personService = personService;
        this.clientService = clientService;
    }

    /**
     * Renders the listing page.
     *
     * @param letter optional letter filter for first name
     * @return list view populated with the current list of people
     */
    @GetMapping(value = "list")
    public ModelAndView list(@RequestParam(value = "letter", required = false) String letter) {
        ModelAndView mav = new ModelAndView("person/list");
        if (letter != null && !letter.isEmpty()) {
            mav.addObject("persons", personService.findByFirstNameStartingWith(letter));
            mav.addObject("selectedLetter", letter.toUpperCase());
        } else {
            mav.addObject("persons", personService.findAll());
        }
        return mav;
    }

    /**
     * Renders an empty form used to create a new person record.
     *
     * @param clientId optional client ID to pre-populate the client field
     * @return create view populated with an empty person
     */
    @GetMapping(value = "create")
    public ModelAndView create(@RequestParam(value="clientId", required = false) Integer clientId) {
        ModelAndView mav = new ModelAndView("person/create");
        Person person = new Person();
        if (clientId != null) {
            person.setClient(clientService.findClientById(clientId));
        }
        mav.addObject("person", person);
        mav.addObject("errors", new ArrayList<String>());
        mav.addObject("clients", clientService.findAll());
        return mav;
    }

    /**
     * Validates and saves a new person.
     * On success, the user is redirected to the listing page.
     * On failure, the form is redisplayed with the validation errors.
     *
     * @param person populated form bean for the person
     * @return redirect, or create view with errors
     */
    @PostMapping(value = "create")
    public ModelAndView create(Person person) {
        if (person.getClient() != null && person.getClient().getClientId() == null) {
            person.setClient(null);
        }
        List<String> errors = personService.validatePerson(person);
        if (errors.isEmpty()) {
            personService.createPerson(person);
            return new ModelAndView("redirect:/person/list");
        } else {
            ModelAndView mav = new ModelAndView("person/create");
            mav.addObject("person", person);
            mav.addObject("clients", clientService.findAll());
            mav.addObject("errors", errors);
            return mav;
        }
    }

    /**
     * gets a read-only view of existing person record
     *
     * @param personId the ID of the person to view
     * @return view page populated from the person record
     */
    @GetMapping(value = "view/{personId}")
    public ModelAndView view(@PathVariable("personId") Integer personId) {
        Person person = personService.findPersonById(personId);
        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        ModelAndView mav = new ModelAndView("person/view");
        mav.addObject("person", person);
        return mav;
    }

    /**
     * Renders an edit form for an existing person record.
     *
     * @param personId the ID of the person to edit
     * @return edit view populated from the person record
     */
    @GetMapping(value = "edit/{personId}")
    public ModelAndView edit(@PathVariable("personId") Integer personId) {
        Person person = personService.findPersonById(personId);
        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        ModelAndView mav = new ModelAndView("person/edit");
        mav.addObject("person", person);
        mav.addObject("clients", clientService.findAll());
        mav.addObject("errors", new ArrayList<String>());
        return mav;
    }

    /**
     * Validates and saves an edited person.
     * On success, the user is redirected to the listing page.
     * On failure, the form is redisplayed with the validation errors.
     *
     * @param person populated form bean for the person
     * @return redirect, or edit view with errors
     */
    @PostMapping(value = "edit")
    public ModelAndView edit(Person person) {
        if (person.getClient() != null && person.getClient().getClientId() == null) {
            person.setClient(null);
        }
        List<String> errors = personService.validatePerson(person);
        if (errors.isEmpty()) {
            personService.updatePerson(person);
            return new ModelAndView("redirect:/person/list");
        } else {
            ModelAndView mav = new ModelAndView("person/edit");
            mav.addObject("person", person);
            mav.addObject("clients", clientService.findAll());
            mav.addObject("errors", errors);
            return mav;
        }
    }

    /**
     * Renders the deletion confirmation page.
     *
     * @param personId the ID of the person to be deleted
     * @return delete view populated from the person record
     */
    @GetMapping(value = "delete/{personId}")
    public ModelAndView delete(@PathVariable("personId") Integer personId) {
        Person person = personService.findPersonById(personId);
        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        ModelAndView mav = new ModelAndView("person/delete");
        mav.addObject("person", person);
        return mav;
    }

    /**
     * Handles person deletion or cancellation, redirecting to the listing page in either case.
     *
     * @param command the command field from the form
     * @param personId the ID of the person to be deleted
     * @return redirect to the listing page
     */
    @PostMapping(value = "delete")
    public String delete(@RequestParam("command") String command, @RequestParam("personId") Integer personId) {
        if (COMMAND_DELETE.equals(command)) {
            personService.deletePerson(personId);
        }
        return "redirect:/person/list";
    }
}
