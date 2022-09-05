package com.example.school.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path = "/students")
public class StudentsController {

    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private ClassesRepository classesRepository;




    String errorNameText = " is not correct. Sample: Иванов Иван Иванович";


    @PostMapping(path = "/add", consumes = MediaType.ALL_VALUE)
    public @ResponseBody
    HttpStatus addNewStudents(
            @RequestParam String name,
            @RequestParam Integer age,
            @RequestParam Integer classId) {

        String[] nameCheck = name.split(" ");
        if (nameCheck.length != 3) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "name " + name + errorNameText);
        }
        if (classesRepository.existsById(classId) == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "class with this ID does not exist");
        }
        Students n = new Students();
        n.setName(name);
        n.setAge(age);
        n.setClassId(classId);
        studentsRepository.save(n);
        return HttpStatus.OK;
    }

    @GetMapping(path = "/all?limit={limit}&page={number")
    public @ResponseBody
    Iterable<Students> getAllStudents() {
        return studentsRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public @ResponseBody
    Students getStudent(@PathVariable("id") Integer id) {

        if (studentsRepository.existsById(id) == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found"
            );
        }
        System.out.println(id);
        Students one = studentsRepository.findById(id).get();
        return one;
    }


    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody
    ResponseEntity<Integer> deleteStudent(@PathVariable("id") Integer id) {

        if (studentsRepository.existsById(id) == true) {
            studentsRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found");
        }
    }


    @GetMapping(value = "/getStudent/{id}")
    public @ResponseBody
    String getStudentAndClass(@PathVariable("id") Integer id) {

        if (studentsRepository.existsById(id) == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found"
            );
        }
        Students one = studentsRepository.findById(id).get();
        Classes classOne = classesRepository.findById(one.getClassId()).get();
        String returnStudent = "name - " + one.getName() + ", age - " + one.getAge() + ", class - " + classOne.getClassNumber() + classOne.getLetter();
        return returnStudent;
    }

    @PutMapping(path = "/put")
    public @ResponseBody
    Students putStudent(@RequestParam Integer id,
                        @RequestParam String name,
                        @RequestParam Integer age,
                        @RequestParam Integer classId) {

        if (studentsRepository.existsById(id) == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found"
            );
        }
        if (studentsRepository.existsById(classId) == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity class id = " + classId + " not found"
            );
        }
        String[] nameCheck = name.split(" ");
        if (nameCheck.length != 3) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "name " + name + errorNameText);
        }
        Students one = studentsRepository.findById(id).get();
        System.out.println(id + name + age + classId);
        one.setName(name);
        one.setAge(age);
        one.setClassId(classId);
        studentsRepository.save(one);
        return one;
    }

    @RequestMapping(value = "/patch/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> patchStudents(
            @RequestBody Map<String, Object> updates,
            @PathVariable("id") Integer id) {
        if (studentsRepository.existsById(id) == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found"
            );
        }
        Students one = studentsRepository.findById(id).get();

        if (updates.containsKey("name") == true) {
            String name = updates.get("name").toString();
            String[] nameCheck = name.split(" ");
            if (nameCheck.length != 3) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "name " + name + errorNameText);
            }
            one.setName(name);
        }
        if (updates.containsKey("age") == true) {
            Integer age = Integer.parseInt(updates.get("age").toString());
            one.setAge(age);
        }
        if (updates.containsKey("classId") == true) {
            Integer classId = Integer.parseInt(updates.get("classId").toString());
            if (studentsRepository.existsById(classId) == false) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "entity class id = " + classId + " not found"
                );
            }
            one.setClassId(classId);
        }
        studentsRepository.save(one);
        return ResponseEntity.ok("student updated");

    }
}

