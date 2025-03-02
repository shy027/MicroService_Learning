package top.shy.orderservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/order")
    public String createOrder(@RequestParam String username,@RequestParam String productId){
//        String userserviceUrl = "http://localhost:8081/user?username=" + username;
//        String userInfo = restTemplate.getForObject(userserviceUrl, String.class);
//        String productServiceUrl = "http://localhost:8083/products?productId=" + productId;
//        String productInfo = restTemplate.getForObject(productServiceUrl, String.class);
        String pyUrl = "http://127.0.0.1:5000/hello";
        String pyInfo = restTemplate.getForObject(pyUrl, String.class);
        return pyInfo;
    }
}
