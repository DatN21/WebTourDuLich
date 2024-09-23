package com.dulich.toudulich.Repositories;

import com.dulich.toudulich.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Integer> {


}
