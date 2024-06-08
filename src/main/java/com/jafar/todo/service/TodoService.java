package com.jafar.todo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.jafar.todo.dao.TodoDao;
import com.jafar.todo.model.Todo;
import com.jafar.todo.model.Toggle;

@Service
public class TodoService {

    @Autowired
    TodoDao todoDao;

    public ResponseEntity<String> addTodoService(Todo todo) {

        try {
            todoDao.save(todo);
            return new ResponseEntity<String>("success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("failure", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Boolean> isTodoClicked(String id) {
        try {
            boolean status = todoDao.findById(id).get().getStatus();
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> toggleTodoCheckBoxService(Toggle toggle) {
        try {
            Optional<Todo> todoOptional = todoDao.findById(toggle.getId());
            if (todoOptional.isPresent()) {
                Todo todo = todoOptional.get();
                todo.setStatus(toggle.isChecked());
                todoDao.save(todo);
                
                return ResponseEntity.ok("\"Todo status updated successfully\"");
            } else {
                return ResponseEntity.status(404).body("Todo not found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occured while updating");
        }
    }

    public ResponseEntity<Map<String, String>> getAllTask(String date) {
        Map<String, String> idDateMap = new HashMap<>();
        try {
            List<Todo> todoForDate = todoDao.findByDate(date);
            for (Todo todo : todoForDate) {
                idDateMap.put(todo.getId(), todo.getTask());
            }

            return ResponseEntity.ok(idDateMap);
        }
        catch (Exception e) {
            return new ResponseEntity<>(idDateMap,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> getTaskOfIdService(String id) {
        try {
            Optional<Todo> todoOptional = todoDao.findById(id);
            if (todoOptional.isPresent()) { 
                Todo todo = todoOptional.get();
                return new ResponseEntity<>(todo.getTask(),HttpStatus.OK);
            } else {
                return ResponseEntity.status(404).body("id not found");
            }
        } catch (Exception err) {
            return ResponseEntity.status(500).body("an error occured while fetching task");
        }
    }

    public ResponseEntity<String> updateTaskOfIdService(String id, String task) {
        try {
            Optional<Todo> todoOptional = todoDao.findById(id);
            if (todoOptional.isPresent()) {
                Todo todo = todoOptional.get();
                todo.setTask(task);
                todoDao.save(todo);
                return ResponseEntity.ok().body("updated task: " +  task +" successfully");
            } else {
                return ResponseEntity.status(404).body("id not found in update");
            }
        } catch (Exception err) {
            return ResponseEntity.status(500).body("an error occured while fetching id");
        }
    }

    public ResponseEntity<String> deleteTodoByIdService(@RequestParam String id) {
        try {
            todoDao.deleteById(id);
            return ResponseEntity.ok().body("Deleted Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("no resource found");
        }
    }

    
}
