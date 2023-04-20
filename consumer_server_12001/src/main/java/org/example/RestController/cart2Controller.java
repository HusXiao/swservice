package org.example.RestController;

import org.example.entity.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpRequest;

@RestController
public class cart2Controller {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getAll")
    public CommonResult get(){
        return restTemplate.getForObject(
                "http://service-provider/user/getAll", CommonResult.class);
    }
    @PostMapping("/login")
    public CommonResult login(HttpRequest request){
        return restTemplate.postForObject(
        "http://service-provider/user/login", request, CommonResult.class);
    }

    @PutMapping("/register")
    public ResponseEntity<CommonResult> register(HttpRequest request){

        return restTemplate.exchange(
                "http://service-provider/user/register", HttpMethod.PUT, null, CommonResult.class);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CommonResult> delete(@PathVariable int id){
        return restTemplate.exchange(
                "http://service-provider/user/login/" + id, HttpMethod.DELETE,null,CommonResult.class);
    }


    public  String fallback(){
        System.out.println("进入回调函数");
        return "hello,world!";
    }

}
