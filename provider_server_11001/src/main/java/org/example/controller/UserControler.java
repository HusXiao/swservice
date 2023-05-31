package org.example.controller;


import org.example.Dao.UserDao;
import org.example.entity.CommonResult;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/user")
@RefreshScope
public class UserControler {

    @Autowired
    UserDao userDao;

    @GetMapping("/getUserById/{userId}")
    public CommonResult<User> getUserById(@PathVariable("userId") Integer userId){
        return new CommonResult(200,"success(负载均衡)",new User(userId,"牛的","123"));
    }

    @GetMapping("/getAll")
    public CommonResult<Collection<User>> getAll(){
        Collection<User> users = userDao.getAll();
        if (users !=null ){
            return new CommonResult<>(200,"搜索成功!(14001)",users);
        }else {
            return new CommonResult<>(404, "查询失败!(14001)", null);
        }

    }

    @PostMapping("/login")
    public CommonResult<User> login(@RequestBody int id, String password){
        User user = userDao.getUserId(id);
        if (user != null){
            if (Objects.equals(user.getPassword(), password)){
                return new CommonResult<>(200,"登录成功(14001)",user);
            }else
            {
                return new CommonResult<>(400, "密码错误(14001)", null);
            }
        }else {
            return new CommonResult<>(404, "用户不存在(14001)", null);
        }
    }

    @PutMapping("/register")
    public CommonResult<User> register(@RequestBody String username,String password){
        User user = new User(0,username,password);
        user = userDao.save(user);
        return new CommonResult<>(200,"注册成功(14001)",user);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult<User> delete(@PathVariable int id){
        if (userDao.getUserId(id) != null){
            userDao.delete(id);
            return new CommonResult<>(200,"删除成功(14001)",null);
        }else {
            return new CommonResult<>(400, "用户不存在(14001)", null);
        }
    }


}
