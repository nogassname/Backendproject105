package com.example.mytodo.controller.todo;

import com.example.mytodo.utils.MySqlConnector;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class CompleteTodo {
    @GetMapping(path = "/complete")
    public Map<String,Object> CompleteTodo(@RequestParam int user_id){
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo WHERE user_id =? and status='1' ");

            preparedStatement.setInt(1, user_id);
            ResultSet resultSet =    preparedStatement.executeQuery();

            ArrayList<Object> complete = new ArrayList<>();
            while(resultSet.next()){
                Map<String,Object> completelist = new HashMap<>();
                completelist.put("todoid", resultSet.getInt("todo_id"));
                completelist.put("date", resultSet.getDate("date"));
                completelist.put("info", resultSet.getString("info"));
                completelist.put("color", resultSet.getString("color"));
                complete.add(completelist);
            }
            res.put("complete",complete);
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
        }
        return res;

    }

}
