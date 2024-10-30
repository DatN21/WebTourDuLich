package com.dulich.toudulich.Repositories;

import com.dulich.toudulich.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserModel,Integer> {
    boolean existsByPhone(String phoneNumber);
    Optional<UserModel> findByPhone(String phoneNumber);
}
