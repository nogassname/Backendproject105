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
public class AllTodo {
    @GetMapping(path = "/all")
    public Map<String, Object> AllTodo(@RequestParam int user_id) {
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM todo WHERE user_id = ?");
            preparedStatement.setInt(1, user_id);
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Object> lists = new ArrayList<>();
            while(resultSet.next()){
                res.put("isFound",true);
                Map<String, Object> list = new HashMap<>();
                list.put("todoid", resultSet.getInt("todo_id"));
                list.put("date", resultSet.getDate("date"));
                list.put("info", resultSet.getString("info"));
                list.put("color", resultSet.getString("color"));
                list.put("status", resultSet.getBoolean("status"));
                lists.add(list);
            }
            res.put("lists", lists);//put array lists ในชื่อlists ในres
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            res.put("success", false);
        }
        return res;

    }
}
