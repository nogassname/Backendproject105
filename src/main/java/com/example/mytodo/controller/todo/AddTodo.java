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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class AddTodo {
    @PostMapping(path = "/create")
    public Map<String,Object> AddTodo(@RequestParam int user_id, @RequestParam String date , @RequestParam String info ,@RequestParam String color){
        Map<String, Object> res = new HashMap<>();
        try {
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into todo (user_id, date, info,color) value (?,?,?,?)");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = new Date(simpleDateFormat.parse(date).getTime());
            preparedStatement.setInt(1, user_id);
            preparedStatement.setDate(2, date1);
            preparedStatement.setString(3, info);
            preparedStatement.setString(4, color);
            preparedStatement.execute();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            res.put("success", false);
        }
        return res;

    }

}
