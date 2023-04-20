package org.example.Dao;


import jakarta.annotation.Resource;
import org.example.entity.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Resource
public class UserDao {
    private static Map<Integer, User> userMap = null;

    static {
        userMap = new HashMap<Integer,User>();
        userMap.put(1, new User(1,"user01","123456"));
        userMap.put(2, new User(2,"user02","123456"));
        userMap.put(3, new User(3,"user03","123456"));
        userMap.put(4, new User(4,"user04","123456"));
        userMap.put(5, new User(5,"user05","123456"));
    }


    //主键自增
    private static Integer initid = 6;
    //增加一个员工
    public User save(User user){
        if (user.getUserId() == 0){
            user.setUserId(initid);
        }
        userMap.put(initid, user);
        initid++;
        return user;
    }

    //查询全部员工信息
    public Collection<User> getAll(){
        return userMap.values();
    }

    //通过id查询员工
    public User getUserId(Integer id){
        return userMap.get(id);
    }

    //通过id删除员工
    public  void delete(Integer id){
        userMap.remove(id);
    }
}
