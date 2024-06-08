package com.jafar.todo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jafar.todo.model.Checker;

public interface CheckDao extends JpaRepository<Checker, Integer> {

    
}
