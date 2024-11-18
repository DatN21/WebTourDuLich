package com.dulich.toudulich.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginDTO {
    @NotBlank(message = "Số điện thoại không được bỏ trống")
    private String phone ;

    @JsonProperty("password")
    @NotBlank(message = "Mật khẩu không đuược bỏ trống")
    private String password ;
}
