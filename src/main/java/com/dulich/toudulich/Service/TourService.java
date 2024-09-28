package com.dulich.toudulich.Service;

import com.dulich.toudulich.DTO.TourDTO;
import com.dulich.toudulich.Model.TourModel;
import com.dulich.toudulich.Repositories.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TourService implements iTourService{
    private final TourRepository tourRepository;


    @Override
    public TourModel createTour(TourDTO tourDTO) {
        TourModel newTour = TourModel.builder()
                .tourName(tourDTO.getTourName())
                .days(tourDTO.getDays())
                .startDate(tourDTO.getStartDate())
                .destination(tourDTO.getDestination())
                .tourType(tourDTO.getTourType())
                .departureLocation(tourDTO.getDepartureLocation())
                .status(tourDTO.getStatus())
                .price(tourDTO.getPrice())
                .build();
        return tourRepository.save(newTour);
    }

    @Override
    public List<TourModel> getAllTour() {
        return tourRepository.findAll();
    }

    @Override
    public TourModel getTourById(int id) {
        return tourRepository.findById(id).orElseThrow(() -> new RuntimeException("Tour with id = " + id + " not found"));
    }

    @Override
    public TourModel updateTour(int tourId, TourDTO tourDTO) {
        TourModel existingTour = getTourById(tourId);
        existingTour.setTourName(tourDTO.getTourName());
        existingTour.setDays(tourDTO.getDays());
        existingTour.setStartDate(tourDTO.getStartDate());
        existingTour.setDestination(tourDTO.getDestination());
        existingTour.setStatus(tourDTO.getStatus());
        existingTour.setPrice(tourDTO.getPrice());
        existingTour.setTourType(tourDTO.getTourType());
        tourRepository.save(existingTour);
        return existingTour;
    }

    @Override
    public void deleteTour(int tourId) {
        tourRepository.deleteById(tourId);
    }
}
