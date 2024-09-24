package com.dulich.toudulich.Service;

import com.dulich.toudulich.DTO.UserDTO;
import com.dulich.toudulich.Model.UserModel;

public interface iUserSevice {
    public UserModel createUserModel(UserDTO userDTO) ;

    public String login(String phoneNumber, String passWord);
}
