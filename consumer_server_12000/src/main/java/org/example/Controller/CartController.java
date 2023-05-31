package org.example.Controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.Entity.CommonResult;
import org.example.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RefreshScope
public class CartController{

    //注入Fegin接口（@EnableFeignClients自动扫描@FeignClient注解）
    @Autowired
    private UserFeignClient userFeignClient;
    @GetMapping("/getUserById/{userId}")
    @CircuitBreaker(name = "CircuitBreakerA", fallbackMethod = "fallback")
    public CommonResult getUserById(@PathVariable("userId") Integer userId) {
        //使用Fegin接口进行服务调用
        return userFeignClient.getUserById(userId);
    }

    @GetMapping("/getAll")
    @CircuitBreaker(name = "ratelimiterA", fallbackMethod = "fallback")
    public CommonResult getAll() {
        //使用Fegin接口进行服务调用
        return userFeignClient.getAll();
    }

    public CommonResult fallback(Throwable throwable){
        CommonResult fall = new CommonResult();
        fall.setMessage("服务降级");
        return fall;
    }

}
