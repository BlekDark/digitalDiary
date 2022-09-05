package com.example.school.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@Controller
@RequestMapping(path = "/marks")
public class MarksController {

    @Autowired
    private MarksRepository marksRepository;

    @PostMapping(path = "/add")
    public @ResponseBody
    String addNewMarks(@RequestParam Integer lessonId,
                       @RequestParam Integer mark,
                       @RequestParam Integer markType,
                       @RequestParam Integer studentsId) {


        Marks m = new Marks();
        m.setLessonId(lessonId);
        m.setMark(mark);
        m.setMarkType(markType);
        m.setStudentsId(studentsId);
        marksRepository.save(m);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Marks> getAllMarks() {
        return marksRepository.findAll();
    }

    @GetMapping(value = "get/{id}")
    public @ResponseBody
    Marks getMarks(@PathVariable("id") Integer id) {
        if (marksRepository.existsById(id) == false) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity id = " + id + " not found"
            );
        }
        System.out.println(id);
        Marks one = marksRepository.findById(id).get();
        return one;
    }

    @DeleteMapping(value = "/delete/{id}")
    public @ResponseBody
    ResponseEntity<Integer> deleteMark(@PathVariable("id") Integer id) {
        if (marksRepository.existsById(id) == true) {
            marksRepository.deleteById(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity id = " + id + " not found");
        }
    }

    @PutMapping(path = "/put")
    public @ResponseBody
    Marks putMark(@RequestParam Integer id,
                  @RequestParam Integer lessonId,
                  @RequestParam Integer mark,
                  @RequestParam Integer markType,
                  @RequestParam Integer studentsId) {
        if (marksRepository.existsById(id) == false) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity id = \" + id + \" not found");
        }
        Marks one = marksRepository.findById(id).get();
        one.setLessonId(lessonId);
        one.setMark(mark);
        one.setMarkType(markType);
        one.setStudentsId(studentsId);
        marksRepository.save(one);
        return one;
    }
}


