package com.jafar.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jafar.todo.dao.CheckDao;
import com.jafar.todo.model.Checker;

@Service
public class CheckService {

    @Autowired
    CheckDao checkDao;
    
    public String add(Checker checker) {
        try {
            checkDao.save(checker);
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failure";

    }
}
