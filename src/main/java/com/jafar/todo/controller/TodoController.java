package com.jafar.todo.controller;

import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jafar.todo.model.Todo;
import com.jafar.todo.model.Toggle;
import com.jafar.todo.service.TodoService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    TodoService todoService;

    @PostMapping("/add_todo")
    public ResponseEntity<String> addTodo(@RequestBody Todo todo) {
        return todoService.addTodoService(todo);
    }

    @GetMapping("/isChecked/{id}")
    public ResponseEntity<Boolean> isTodoChecked(@PathVariable("id") String id) {
        return todoService.isTodoClicked(id);
    }

    @PutMapping("/toggleTodo")
    public ResponseEntity<String> toggleTodoCheckBox(@RequestBody Toggle toggle) {
        return todoService.toggleTodoCheckBoxService(toggle);
    }

    @PostMapping("/getAllTask")
    public ResponseEntity<Map<String, String>> getAllTask(@RequestBody String date) {
        return todoService.getAllTask(date);
    }

    @GetMapping("/getTaskId/{id}")
    public ResponseEntity<String> getTaskOfId(@PathVariable String id) {
        return todoService.getTaskOfIdService(id);   
    }

    @PatchMapping("/updateTask/{id}")
    public ResponseEntity<String> updateTaskOfId(@PathVariable String id, @RequestBody String task) {
        return todoService.updateTaskOfIdService(id,task);
    }

    @DeleteMapping("/deleteTodo/{id}")
    public ResponseEntity<String> deleteTodoById(@RequestBody String id) {
        return todoService.deleteTodoByIdService(id);
    }
}
