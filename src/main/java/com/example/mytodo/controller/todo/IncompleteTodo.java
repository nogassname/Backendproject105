package com.example.mytodo.controller.todo;

import com.example.mytodo.utils.MySqlConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class IncompleteTodo {
    @GetMapping(path = "/incomplete")
    public Map<String,Object> CompleteTodo(@RequestParam int user_id){
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo WHERE user_id =? and status='0' ");

            preparedStatement.setInt(1, user_id);
            ResultSet resultSet =    preparedStatement.executeQuery();

            ArrayList<Object> incomplete = new ArrayList<>();
            while(resultSet.next()){
                Map<String,Object> incompletelist = new HashMap<>();
                incompletelist.put("todoid", resultSet.getInt("todo_id"));
                incompletelist.put("date", resultSet.getDate("date"));
                incompletelist.put("info", resultSet.getString("info"));
                incompletelist.put("color", resultSet.getString("color"));
                incomplete.add(incompletelist);
            }
            res.put("incomplete",incomplete);
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
        }
        return res;

    }
}
