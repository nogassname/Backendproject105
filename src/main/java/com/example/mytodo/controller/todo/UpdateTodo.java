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
public class UpdateTodo {
    @PostMapping(path = "/info")
    public Map<String,Object> UpdateTodo(@RequestParam int todo_id,@RequestParam int user_id,@RequestParam long date , @RequestParam String info , @RequestParam String color,@RequestParam Boolean status){
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE  todo set todo_id=?,user_id=?, date=?, info=?,color=?,status=?");
            Date date2 = new Date(date);
            preparedStatement.setInt(1, todo_id);
            preparedStatement.setInt(2, user_id);
            preparedStatement.setDate(3, (java.sql.Date) date2);
            preparedStatement.setString(4, info);
            preparedStatement.setString(5, color);
            preparedStatement.setBoolean(5, status);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
        }
        return res;

    }
}
