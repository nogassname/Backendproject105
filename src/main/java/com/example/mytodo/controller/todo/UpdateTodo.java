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
    @PostMapping(path = "/edit/update")
    public Map<String,Object> UpdateTodo(@RequestParam int todo_id,@RequestParam int user_id,@RequestParam long date , @RequestParam String info , @RequestParam String color,@RequestParam Boolean status){
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE  todo SET   date=?, info=?,color=?,status=? WHERE todo_id = ? and user_id =?" +
                    " ");
            Date date2 = new Date(date);
            preparedStatement.setInt(5, todo_id);
            preparedStatement.setInt(6, user_id);
            preparedStatement.setDate(1, (java.sql.Date) date2);
            preparedStatement.setString(2, info);
            preparedStatement.setString(3, color);
            preparedStatement.setBoolean(4, status);

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
        }
        return res;

    }

    @PostMapping(path = "/edit/status")
    public void UpdateTodo(@RequestParam int todo_id, @RequestParam Boolean status){
        Map<String, Object> res = new HashMap<>();
        try {
            System.out.println(status);
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE  todo SET status = ? WHERE todo_id = ? ");
            preparedStatement.setInt(2, todo_id);
            preparedStatement.setBoolean(1,status);
            preparedStatement.execute();
            System.out.println("Update successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
