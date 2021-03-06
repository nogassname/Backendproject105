package com.example.mytodo.controller.todo;

import com.example.mytodo.utils.MySqlConnector;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class DeleteTodo {


    @PostMapping(path = "/edit/delete")
    public void DeleteTodo(@RequestParam int todo_id) {
        try {
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from todo where todo_id = ? ");
            preparedStatement.setInt(1, todo_id);
            preparedStatement.execute();
            System.out.println("Already delete");
        } catch (SQLException e) {
            System.out.println("Delete fail");
            e.printStackTrace();
        }
    }
}


