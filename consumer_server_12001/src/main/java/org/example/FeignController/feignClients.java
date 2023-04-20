package org.example.FeignController;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "provider-server")
public interface feignClients {
    @GetMapping("/hello/China/{name}")
    public String hello(@PathVariable String name);
}
