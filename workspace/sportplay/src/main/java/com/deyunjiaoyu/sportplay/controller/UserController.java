package com.deyunjiaoyu.sportplay.controller;

import com.alibaba.fastjson.JSON;
import com.deyunjiaoyu.sportplay.bean.QueryInfo;
import com.deyunjiaoyu.sportplay.bean.User;
import com.deyunjiaoyu.sportplay.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao udao;

    @RequestMapping("/alluser")
    public String getUserList(QueryInfo queryInfo){

        System.out.println(queryInfo);
        int numbers = udao.getUserCounts("%"+queryInfo.getQuery()+"%");// 获取数据总数
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        List<User> users = udao.getAllUser("%"+queryInfo.getQuery()+"%",pageStart,queryInfo.getPageSize());
        HashMap<String, Object> res = new HashMap<>();
        res.put("numbers",numbers);
        res.put("data",users);
        System.out.println("总条数："+numbers);
        System.out.println(users);
        String res_string = JSON.toJSONString(res);
        return res_string;
    }

    @RequestMapping("/userstate")
    public String updateUserState(@RequestParam("id") Integer  id,
                                  @RequestParam("state") Boolean state){
        int i = udao.updateState(id, state);
        System.out.println("用户编号:"+id);
        System.out.println("用户状态:"+state);
        String str = i >0?"success":"error";
        return str;
    }

    @RequestMapping("/addUser")
    public String addUser(@RequestBody User user){
        System.out.println(user);
        user.setRole("普通用户");
        user.setState(false);
        int i = udao.addUser(user);
        String str = i >0?"success":"error";
        return str;
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(int id){
        System.out.println(id);
        int i = udao.deleteUser(id);
        String str = i >0?"success":"error";
        return str;
    }

    @RequestMapping("/getUpdate")
    public String getUpdateUser(int id){
        System.out.println("编号:"+id);
        User updateUser = udao.getUpdateUser(id);
        String users_json = JSON.toJSONString(updateUser);
        return users_json;
    }
    @RequestMapping("/editUser")
    public String editUser(@RequestBody User user){
        System.out.println(user);
        int i = udao.editUser(user);
        String str = i >0?"success":"error";
        return str;
    }
}
