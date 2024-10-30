package com.dulich.toudulich.Service;

import com.dulich.toudulich.DTO.UserDTO;
import com.dulich.toudulich.Model.UserModel;
import com.dulich.toudulich.exceptions.DataNotFoundException;
import com.dulich.toudulich.exceptions.InvalidParamException;
import com.dulich.toudulich.exceptions.PermissionDenyException;
import org.springframework.stereotype.Service;

@Service
public interface iUserService {
    UserModel createUser(UserDTO userDTO) throws PermissionDenyException;

    String login(String phoneNumber , String password) throws DataNotFoundException, InvalidParamException;

}
