package com.example.restaurant.services.servicesImp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.restaurant.Result;
import com.example.restaurant.mapper.LoginMapper;
import com.example.restaurant.pojo.BusinessUser;
import com.example.restaurant.pojo.LoginUser;
import com.example.restaurant.pojo.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private LoginMapper loginMapper;

    public UserDetailsServiceImp(){};

    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
//        LambdaQueryWrapper<BusinessUser> queryWrapper = new LambdaQueryWrapper();
//        queryWrapper.eq(BusinessUser::getEmail, userEmail);
//        BusinessUser businessUser = this.loginMapper.selectOne(queryWrapper);
          BusinessUser businessUser = loginMapper.selectByEmail(userEmail);
        if (Objects.isNull(businessUser)) {
            throw new RuntimeException("Wrong email or password");
        } else {

//            TO DO: 根据查询用户权限信息，添加到LoginUser中
            return new LoginUser(businessUser);

        }
    }


}
