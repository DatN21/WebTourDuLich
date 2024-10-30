package com.dulich.toudulich.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Roles")
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "role_name",nullable = false)
    String roleName;

    public static String ADMIN = "ADMIN" ;
    public static String USER = "USER" ;
}
