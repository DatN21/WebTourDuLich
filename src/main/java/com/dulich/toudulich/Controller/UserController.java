package com.dulich.toudulich.Controller;

import com.dulich.toudulich.DTO.UserDTO;
import com.dulich.toudulich.Service.iUserService;
import com.dulich.toudulich.exceptions.DataNotFoundException;
import com.dulich.toudulich.exceptions.InvalidParamException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
    private final iUserService userService ;
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok("Ok");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){
        try {
            if (bindingResult.hasErrors()) {
                List<String> errorMessages = bindingResult.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
                return ResponseEntity.badRequest().body(errorMessages);
            } else {
                userService.createUser(userDTO);
                return ResponseEntity.ok(userDTO);
            }
        } catch (Exception var4) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(var4.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserDTO userLoginDTO) throws DataNotFoundException, InvalidParamException {
        String token = userService.login(userLoginDTO.getPhone(), userLoginDTO.getPassword());
        return ResponseEntity.ok(token) ;
    }
}
