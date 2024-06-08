package com.jafar.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jafar.todo.model.Checker;
import com.jafar.todo.service.CheckService;

@RestController
@RequestMapping("Rough")
public class RoughController {


    @Autowired
    CheckService checkService;

    
    @PostMapping("/addChecker")
    public String addChecker(@RequestBody Checker checker) {
        String output = checkService.add(checker);
        return output;
    }
}
