package com.example.mytodo.controller.account;

import com.example.mytodo.utils.MySqlConnector;
import org.springframework.web.bind.annotation.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/account")
public class Login {

    @PostMapping(path = "/login")
    public Map<String, Object> login(@RequestParam String username, @RequestParam String password){

        Map<String, Object> res = new HashMap<>();
        try{
            Connection connection = MySqlConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from user where username=? and password=?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();//เมื่อexecuteได้รับข้อมูลมาเป็น(query)เลยไปเก็บในresultset(ใช้ไม่เหมือนกับregiเพราะเลือกข้อมูลในตาราง)

            if(resultSet.next()){ // .next เพราะว่าresultsetที่0เป็นnull เราเลยต้อง.next เพื่อเป็น1234...
                Map<String, Object> user = new HashMap<>();
                user.put("username", resultSet.getString("username"));
                res.put("user", user);//ตัวแปรเอาไปใช้ในฟ้อนได้ (_,_ ข้างหลังคือค่าข้างหน้าคือชื่อ)
                res.put("isLogin", true);
            }
            else {
                res.put("isLogin", false);
                res.put("error", "username or password incorrect");
            }
        }catch (SQLException e){
            e.printStackTrace();
            res.put("success", false);
        }
        return res;
    }

}
