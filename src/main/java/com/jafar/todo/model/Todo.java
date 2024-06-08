package com.jafar.todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Todo {

    @Id
    private String id;
    private String task;
    private String date;
    private boolean status;

    public boolean getStatus() {
        return status;
    }
    
}
