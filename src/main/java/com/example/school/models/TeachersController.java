package com.example.school.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Controller
@RequestMapping(path = "/teachers")
public class TeachersController {

    @Autowired
    private TeachersRepository teachersRepository;

    @PostMapping(path = "/add", consumes = MediaType.ALL_VALUE)
    public @ResponseBody
    HttpStatus addNewTeacher(
            @RequestParam String name) {

        String[] nameCheck = name.split(" ");
        if (nameCheck.length != 3) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "name " + name + " is not correct. Sample: Иванов Иван Иванович");
        }
        Teachers one = new Teachers();
        one.setName(name);
        teachersRepository.save(one);
        return HttpStatus.OK;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Teachers> getAllStudents() {
        return teachersRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody
    Teachers getTeachers(@PathVariable("id") Integer id) {

        if (teachersRepository.existsById(id) == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found"
            );
        }
        System.out.println(id);
        Teachers one = teachersRepository.findById(id).get();
        return one;
    }


    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody
    ResponseEntity<Integer> deleteTeacher(@PathVariable("id") Integer id) {

        if (teachersRepository.existsById(id) == true) {
            teachersRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found");
        }
    }


    @PutMapping(path = "/put")
    public @ResponseBody
    Teachers putTeacher(@RequestParam Integer id,
                        @RequestParam String name) {

        if (teachersRepository.existsById(id) == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found"
            );
        }
        String[] nameCheck = name.split(" ");
        if (nameCheck.length != 3) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "name " + name + " is not correct. Sample: Иванов Иван Иванович");
        }
        Teachers one = teachersRepository.findById(id).get();
        one.setName(name);
        teachersRepository.save(one);
        return one;
    }
}
