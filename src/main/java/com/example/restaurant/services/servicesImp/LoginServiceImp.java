package com.example.restaurant.services.servicesImp;


import com.example.restaurant.Result;
import com.example.restaurant.entities.BusinessUser;
import com.example.restaurant.entities.Token;
import com.example.restaurant.mapper.LoginMapper;
import com.example.restaurant.entities.LoginUser;
import com.example.restaurant.mapper.TokenMapper;
import com.example.restaurant.services.LoginService;
import com.example.restaurant.utils.JwtUtil;
import com.example.restaurant.utils.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImp implements LoginService {
    private final String emailFormat = "^[a-z0-9]*[@][a-z0-9]*([.][a-z]*)*$";
    private final String passwordFormat = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$";
    private static Logger logger= LoggerFactory.getLogger(LoginServiceImp.class);
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    LoginMapper loginMapper;
    @Autowired
    TokenMapper tokenMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    public Result userLogin(Map<String,String> loginInfo) {
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginInfo.get("email"), loginInfo.get("password"));
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            return Result.error("Account doesn't exist, or incorrect password");
        }
        // 认证通过，通过user email生成一个jwt， jwt存入response result返回
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userEmail = loginUser.getUsername();
        String userId = String.valueOf(loginUser.getId());
        String jwt = JwtUtil.createJWT(userEmail);

        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        tokenMapper.insert(new Token(jwt,userId));
//        redisCache.setCacheObject("login:" + user_email, loginUser,1, TimeUnit.DAYS);
//
        return Result.success(map);



//        BusinessUser businessUser = loginMapper.selectByEmail(email);
//        if(businessUser==null){
//            return Result.error("The account of this email doesn't exist");
//        }else if(!businessUser.getPassword().equals(password)){
//            return Result.error("Incorrect password");
//        }else {
//            return Result.success(businessUser);
//        }

    }


    public Result userLogout(){
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        String token = (String)authentication.getCredentials();
        try {
//            redisCache.deleteObject("Login: " + userEmail);
            tokenMapper.deleteById(token);
        }catch (Exception e){
            return Result.error("Fail to log out, please try again");
        }

        return Result.success("Log out successfully!");

    }


    public Result userSignUp(Map<String, String> signupInfo){

        if(!signupInfo.get("email").matches(emailFormat)){
            return Result.error("Incorrect email format");
        }
        if(!signupInfo.get("password").matches(passwordFormat)){
            return Result.error("Invalid password. The length of password should be 8-16, " +
                    "containing at least one upper case letter, one lower case letter and one number");
        }



        BusinessUser oldBusinessUser = loginMapper.selectByEmail(signupInfo.get("email"));
        if(oldBusinessUser!= null){
//            System.out.println(oldBusinessUser);
            return Result.error("This email has been used");

        }else {
            BusinessUser newBusinessUser = new BusinessUser(signupInfo.get("fullName"), signupInfo.get("email"),passwordEncoder.encode(signupInfo.get("password")));
            loginMapper.insert(newBusinessUser);
            return userLogin(signupInfo);
//            return null;
        }
    }





}
