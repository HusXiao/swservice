package org.example.FeignController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class cartController {
    //注入Feign接口（@EnableFeignClients自动扫描@FeignClient注解）
    @Autowired
    private feignClients feignClients;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name){
       return feignClients.hello(name);
    }

}