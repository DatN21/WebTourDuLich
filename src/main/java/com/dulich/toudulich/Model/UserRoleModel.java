package com.dulich.toudulich.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "userRoles")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotNull(message = "UserId can't be empty")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    UserModel user;

    @NotNull(message = "RoleId can't be empty")
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    RoleModel role;



}
