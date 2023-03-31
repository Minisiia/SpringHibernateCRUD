package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.dao.PersonDAO;
import com.example.hibernatedemo.entity.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping(value = "/index")
    public String indexAllForms(@RequestParam(value = "myName", required = false) String myName,
                                @RequestParam(value = "myAge", required = false) Integer myAge,
                                Model model) {
        model.addAttribute("people", personDAO.index());
        model.addAttribute("findByName", personDAO.findByName(myName));
        model.addAttribute("findByAge", personDAO.findByAge(myAge));
        return "people/index";
    }

    @PostMapping("/find-by-name")
    public String findByName(@RequestParam(value = "myName", required = false) String myName,
                             Model model) {
        model.addAttribute("myName", myName);
        model.addAttribute("findByName", personDAO.findByName(myName));
        return "redirect:/people/index";
    }

    @PostMapping("/find-by-age")
    public String findByAge(@RequestParam(value = "myAge", required = false) Integer myAge,
                            Model model) {
        model.addAttribute("myAge", myAge);
        model.addAttribute("findByAge", personDAO.findByAge(myAge));
        return "redirect:/people/index";
    }

    @PostMapping("/update-person-name-by-id")
    public String updatePersonNameById(@RequestParam("myUpdateName") String myUpdateName,
                                     @RequestParam("myUpdateId") int myUpdateId,
                                     Model model) {
        model.addAttribute("myUpdateName", myUpdateName);
        model.addAttribute("myUpdateId", myUpdateId);
        personDAO.updateNameById(myUpdateId, myUpdateName);
        return "redirect:/people/index";
    }

    @PostMapping("/update-person-email-by-name")
    public String updateEmailByName(@RequestParam("myUpdateNameEmail") String myUpdateNameEmail,
                                     @RequestParam("myUpdateEmail") String myUpdateEmail,
                                     Model model) {
        model.addAttribute("myUpdateNameEmail", myUpdateNameEmail);
        model.addAttribute("myUpdateEmail", myUpdateEmail);
        personDAO.updateEmailByName(myUpdateNameEmail,myUpdateEmail);
        return "redirect:/people/index";
    }

    @PostMapping("/delete-by-name")
    public String deleteByName(@RequestParam("myDeleteName") String myDeleteName) {
        personDAO.deletePersonByName(myDeleteName);
        return "redirect:/people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @PostMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

}
