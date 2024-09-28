package com.dulich.toudulich.Service;

import com.dulich.toudulich.DTO.TourDTO;
import com.dulich.toudulich.Model.TourModel;

import java.util.List;

public interface iTourService {
    TourModel createTour(TourDTO tour);
    List<TourModel> getAllTour();
    TourModel getTourById(int id);
    TourModel updateTour(int tourId,TourDTO tour);
    void deleteTour(int tourId);
}
