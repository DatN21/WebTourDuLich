package com.dulich.toudulich.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleDTO {

    @NotNull(message = "UserId can't be empty")
    @JsonProperty("user_id")
    int userId;

    @NotNull(message = "RoleId can't be empty")
    @JsonProperty("role_id")
    int roleId;

}
