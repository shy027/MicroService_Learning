package top.shy.userservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import top.shy.userservice.entity.User;
import top.shy.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
//    @GetMapping("/user")
//    public String getUser(@RequestParam String username){
//        return "User:" + username;
//    }
    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getById(id);
    }
}
