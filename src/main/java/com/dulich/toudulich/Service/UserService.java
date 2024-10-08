package com.dulich.toudulich.Service;

import com.dulich.toudulich.DTO.UserDTO;
import com.dulich.toudulich.Model.UserModel;
import com.dulich.toudulich.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements iUserSevice{
    private final UserRepository userRepository ;
    @Override
    public UserModel createUserModel(UserDTO userDTO) {
        UserModel newUserModel = UserModel.builder().
                name(userDTO.getName())
                .phone(userDTO.getPhone())
                .gender(userDTO.getGender())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .passWord(userDTO.getPassword())
                .build();
        return userRepository.save(newUserModel);
    }

    @Override
    public String login(String phoneNumber, String passWord) {
        return "";
    }
}
