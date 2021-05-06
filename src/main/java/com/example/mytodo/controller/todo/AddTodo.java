package com.example.mytodo.controller.todo;

import com.example.mytodo.utils.MySqlConnector;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class AddTodo {
    @PostMapping(path = "/create")
    public Map<String,Object> AddTodo(@RequestParam int user_id, @RequestParam long date , @RequestParam String info ,@RequestParam String color){
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into todo (user_id, date, info,color) value (?,?,?,?)");
            Date date1 = new Date(date);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setDate(2, (java.sql.Date) date1);
            preparedStatement.setString(3, info);
            preparedStatement.setString(4, color);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
        }
        return res;

    }

}
