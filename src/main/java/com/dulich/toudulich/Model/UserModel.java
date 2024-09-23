package com.dulich.toudulich.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id ;

    @Column(name = "name",nullable = false,length = 255)
    private String name ;

    @Column(name = "phone",nullable = false,length = 15)
    private String phone ;

    @Column(name = "gender",nullable = false,length = 10)
    private String gender ;

    @Column(name = "email",nullable = false,length = 255)
    private String email ;

    @Column(name = "address",nullable = false,length = 255)
    private String address ;
}
