package com.example.school.models;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.Entity;
import java.util.Map;

@Controller
@RequestMapping(path = "/markTypes")
public class MarkTypesController {

    @Autowired
    public MarkTypesRepository markTypesRepository;

    @PostMapping(path = "/add")
    public @ResponseBody
    HttpStatus addNewMarkTypes(
            @RequestParam String type) {
        if (type instanceof String) {
            MarkTypes m = new MarkTypes();
            m.setType(type);
            markTypesRepository.save(m);
            return HttpStatus.OK;
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity types = " + type.toString() + " not String"
        );
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<MarkTypes> getAllMarkType() {
        return markTypesRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody
    MarkTypes getMarkType(@PathVariable("id") Integer id) {
        if (markTypesRepository.existsById(id) == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found"
            );
        }
        MarkTypes one = markTypesRepository.findById(id).get();
        return one;
    }

    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody
    ResponseEntity<Integer> deleteMarkType(@PathVariable("id") Integer id) {

        if (markTypesRepository.existsById(id) == true) {
            markTypesRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found");
        }
    }

    @PutMapping(path = "/put") // put = path
    public @ResponseBody
    MarkTypes putMarkType(@RequestParam Integer id,
                          @RequestParam String type) {

        if (markTypesRepository.existsById(id) == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found"
            );
        }
        MarkTypes one = markTypesRepository.findById(id).get();
        one.setType(type);
        markTypesRepository.save(one);
        return one;
    }

}
