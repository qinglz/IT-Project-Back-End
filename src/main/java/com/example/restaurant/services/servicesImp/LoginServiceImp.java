package com.example.restaurant.services.servicesImp;


import com.example.restaurant.Result;
import com.example.restaurant.pojo.BusinessUser;
import com.example.restaurant.repository.LoginRepository;
import com.example.restaurant.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService {
    private final String emailFormat = "^[a-z0-9]*[@][a-z0-9]*[.][a-z]*$";
    private final String passwordFormat = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$";
    private static Logger logger= LoggerFactory.getLogger(LoginServiceImp.class);

    @Autowired
    LoginRepository loginRepository;

    public Result userLogin(String email, String password){
        BusinessUser businessUser = loginRepository.findBusinessUserByEmail(email);
        if(businessUser==null){
            return Result.error("The account of this email doesn't exist");
        }else if(!businessUser.getPassword().equals(password)){
            return Result.error("Incorrect password");
        }else {
            return Result.success(businessUser);
        }

    }
    public Result userSignUp(String name, String email, String password){

        if(!email.matches(emailFormat)){
            return Result.error("Incorrect email format");
        }
        if(!password.matches(passwordFormat)){
            return Result.error("Weak password. One upper case letter, one lower case letter and one number are required");
        }



        BusinessUser businessUser = loginRepository.findBusinessUserByEmail(email);
        if(businessUser != null){
            return Result.error("This email has been used");
        }else {
            BusinessUser newBusinessUser = new BusinessUser(name,email,password);
            loginRepository.insert(newBusinessUser);
            return Result.success(newBusinessUser);
        }
    }





}
