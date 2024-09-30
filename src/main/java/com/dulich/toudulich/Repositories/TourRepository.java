package com.dulich.toudulich.Repositories;

import com.dulich.toudulich.Model.TourModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TourRepository extends JpaRepository<TourModel, Integer> {

    Page<TourModel> findAll(Pageable pageable);

    //boolean existsByName(String name);

}
