package top.shy.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.shy.userservice.entity.User;
import top.shy.userservice.mapper.UserMapper;
import top.shy.userservice.service.UserService;



@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getById(Long id) {
        // 创建一个 QueryWrapper
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "user_name", "avatar_url");  // 指定需要查询的字段
        queryWrapper.eq("id", id);  // 条件：id 等于传入的 id

        // 执行查询
        return this.getOne(queryWrapper);
    }
}
