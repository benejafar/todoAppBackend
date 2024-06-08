package com.jafar.todo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jafar.todo.model.Todo;

public interface TodoDao extends JpaRepository<Todo,String> {

    @Query(value = "SELECT * FROM todo t WHERE t.date =:date;", nativeQuery = true)
    List<Todo> findByDate(String date);
    
}
