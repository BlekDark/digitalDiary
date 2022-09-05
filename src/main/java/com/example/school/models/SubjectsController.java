package com.example.school.models;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping(path = "/subjects")
public class SubjectsController {

@Autowired
    private SubjectsRepository subjectsRepository;

    @PostMapping(path = "/add", consumes = MediaType.ALL_VALUE)
    public @ResponseBody
    HttpStatus addNewSubjects(
            @RequestParam String name){
        Subjects one = new Subjects();
        one.setName(name);
        subjectsRepository.save(one);
        return HttpStatus.OK;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Subjects> getAllSubjects(){
        return subjectsRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody
    Subjects getSubject(@PathVariable("id") Integer id){

        if (subjectsRepository.existsById(id) == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found"
            );
        }
        Subjects one = subjectsRepository.findById(id).get();
        return one;

    }

    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody
    ResponseEntity<Integer> deleteSubject(@PathVariable("id") Integer id) {

        if (subjectsRepository.existsById(id) == true) {
            subjectsRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found");
        }
    }


}
