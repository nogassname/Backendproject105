package com.example.mytodo.controller.account;

import com.example.mytodo.utils.MySqlConnector;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController//เพื่อเชื่อมกับฟ้อน?
@RequestMapping("/account")
public class Register {
    @PostMapping(path = "/register")
    public Map<String, Object> register(@RequestParam String username, @RequestParam String email, @RequestParam String password){

        Map<String, Object> res = new HashMap<>();
        try{
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT into user (username, email, password) value (?,?,?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.execute();//ให้ข้างบน4บรรทัดทำงานเหมือนข้างบนเป็นการเตรียมการแล้วมาexecute
        }
        catch (SQLException e){
            e.printStackTrace();
            res.put("success", false);
        }
        return res;//ส่งresไปให้ฟ้อน
    }
}
