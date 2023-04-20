package org.example.Controller;

import org.example.Entity.CommonResult;
import org.example.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cart")
public class CartController{

    //注入Fegin接口（@EnableFeignClients自动扫描@FeignClient注解）
    @Autowired
    private UserFeignClient userFeignClient;
    @GetMapping("/getUserById/{userId}")
    public CommonResult getUserById(@PathVariable("userId") Integer userId) {
        //使用Fegin接口进行服务调用
        return userFeignClient.getUserById(userId);
    }
}
