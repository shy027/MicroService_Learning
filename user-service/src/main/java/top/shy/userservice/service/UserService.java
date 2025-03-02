package top.shy.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.shy.userservice.entity.User;
public interface UserService {
    User getById(Long id);
}
