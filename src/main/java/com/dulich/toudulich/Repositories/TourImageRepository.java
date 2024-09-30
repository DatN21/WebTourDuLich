package com.dulich.toudulich.Repositories;

import com.dulich.toudulich.Model.TourImageModel;
import com.dulich.toudulich.Model.TourModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourImageRepository extends JpaRepository<TourImageModel,Integer> {
    List<TourImageModel> findByTourModel(TourModel tourModel);

}
