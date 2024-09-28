package com.dulich.toudulich.Repositories;

import com.dulich.toudulich.Model.TourModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<TourModel, Integer> {
}
