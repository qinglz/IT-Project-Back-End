package com.example.restaurant.services.servicesImp;


import com.example.restaurant.Result;
import com.example.restaurant.pojo.BusinessUser;
import com.example.restaurant.mapper.LoginMapper;
import com.example.restaurant.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImp implements LoginService {
    private final String emailFormat = "^[a-z0-9]*[@][a-z0-9]*[.][a-z]*$";
    private final String passwordFormat = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$";
    private static Logger logger= LoggerFactory.getLogger(LoginServiceImp.class);
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Result userLogin(BusinessUser businessUser){
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(businessUser.getEmail(), businessUser.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if (Objects.isNull(authentication)){
            throw new RuntimeException("Login Failed");
        }

        return Result.success(authentication);


//        BusinessUser businessUser = loginMapper.selectByEmail(email);
//        if(businessUser==null){
//            return Result.error("The account of this email doesn't exist");
//        }else if(!businessUser.getPassword().equals(password)){
//            return Result.error("Incorrect password");
//        }else {
//            return Result.success(businessUser);
//        }

    }


    public Result userSignUp(BusinessUser businessUser){

        if(!businessUser.getEmail().matches(emailFormat)){
            return Result.error("Incorrect email format");
        }
        if(!businessUser.getPassword().matches(passwordFormat)){
            return Result.error("Invalid password. The length of password should be 8-16, " +
                    "containing at least one upper case letter, one lower case letter and one number");
        }



        BusinessUser oldBusinessUser = loginMapper.selectByEmail(businessUser.getEmail());
        if(oldBusinessUser!= null){
            return Result.error("This email has been used");
        }else {
            BusinessUser newBusinessUser = new BusinessUser(businessUser.getName(), businessUser.getEmail(),passwordEncoder.encode(businessUser.getPassword()));
            loginMapper.insert(newBusinessUser);
            return Result.success(userLogin(businessUser));
        }
    }





}
