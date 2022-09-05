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
@RequestMapping(path = "/classes")
public class ClassesController {

    @Autowired
    private ClassesRepository classesRepository;

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewStudents(
            @RequestParam Integer classNumber,
            @RequestParam Character letter) {

        if (classNumber < 1 || classNumber > 11) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "number class " + classNumber + " is not correct.");
        }

        Classes n = new Classes();
        n.setClassNumber(classNumber);
        n.setLetter(letter);
        classesRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Classes> getAllClasses() {
        return classesRepository.findAll();
    }


    @GetMapping(value = "/{id}")
    public @ResponseBody
    Classes getClass(@PathVariable("id") Integer id) {

        if (classesRepository.existsById(id) == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found"
            );
        }
        Classes one = classesRepository.findById(id).get();
        System.out.println(one);
        return one;
    }

    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody
    ResponseEntity<Integer> deleteClass(@PathVariable("id") Integer id) {

        if (classesRepository.existsById(id) == true) {
            classesRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found");
        }
    }

    @PutMapping(path = "/put")
    public @ResponseBody
    Classes putClass(@RequestParam Integer id,
                  @RequestParam Integer classNumber,
                  @RequestParam Character letter) {
        if (classesRepository.existsById(id) == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity id = \" + id + \" not found");
        }
        if (classNumber < 1 || classNumber > 11) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "number class " + classNumber + " is not correct.");
        }
        Classes one = classesRepository.findById(id).get();
        one.setClassNumber(classNumber);
        one.setLetter(letter);
        classesRepository.save(one);
        return one;
    }


    @RequestMapping(value = "/patch/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchSClass(
            @RequestBody Map<String, Object> updates,
            @PathVariable("id") Integer id) {
        if (classesRepository.existsById(id) == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found"
            );
        }
        Classes one = classesRepository.findById(id).get();

        if (updates.containsKey("classNumber") == true) {
            Integer classNumber = Integer.parseInt(updates.get("classNumber").toString());
            if (classNumber < 1 || classNumber > 11) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "class number  " + classNumber + " is not correct.");
            }
            one.setClassNumber(classNumber);
        }
        if (updates.containsKey("letter") == true) {
            String letter = updates.get("age").toString();
            if (letter.length() != 1) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "letter " + letter + " is more one symbol");
            }
            one.setLetter(letter.charAt(0));
        }

        classesRepository.save(one);
        return ResponseEntity.ok("class updated");

    }
}