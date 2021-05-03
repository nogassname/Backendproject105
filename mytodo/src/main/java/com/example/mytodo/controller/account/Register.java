package com.example.mytodo.controller.account;

import com.example.mytodo.utils.MySqlConnector;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class Register {
    @PostMapping(path = "/register")
    public Map<String, Object> register(@RequestParam String user, @RequestParam String email, @RequestParam String password){

        Map<String, Object> res = new HashMap<>();
        try{
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into user (user, email, password) value (?,?,?)");
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.execute();
            res.put("success", true);
        }
        catch (SQLException e){
            e.printStackTrace();
            res.put("success", false);
        }
        return res;
    }
}
