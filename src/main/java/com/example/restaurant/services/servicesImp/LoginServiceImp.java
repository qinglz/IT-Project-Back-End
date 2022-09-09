package com.example.restaurant.services.servicesImp;


import com.example.restaurant.Result;
import com.example.restaurant.pojo.BusinessUser;
import com.example.restaurant.mapper.LoginMapper;
import com.example.restaurant.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService {
    private final String emailFormat = "^[a-z0-9]*[@][a-z0-9]*[.][a-z]*$";
    private final String passwordFormat = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$";
    private static Logger logger= LoggerFactory.getLogger(LoginServiceImp.class);

    @Autowired
    LoginMapper loginMapper;

    public Result userLogin(String email, String password){
        BusinessUser businessUser = loginMapper.selectByEmail(email);
        if(businessUser==null){
            return Result.error("The account of this email doesn't exist");
        }else if(!businessUser.getPassword().equals(password)){
            return Result.error("Incorrect password");
        }else {
            return Result.success(businessUser);
        }

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
            loginMapper.insert(businessUser);
            return Result.success(businessUser);
        }
    }





}
