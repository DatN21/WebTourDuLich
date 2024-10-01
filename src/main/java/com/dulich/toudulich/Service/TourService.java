package com.dulich.toudulich.Service;

import com.dulich.toudulich.DTO.TourDTO;
import com.dulich.toudulich.DTO.TourImageDTO;
import com.dulich.toudulich.Model.TourImageModel;
import com.dulich.toudulich.Model.TourModel;
import com.dulich.toudulich.Repositories.TourImageRepository;
import com.dulich.toudulich.Repositories.TourRepository;
import com.dulich.toudulich.responses.TourResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TourService implements iTourService {
    private final TourRepository tourRepository;
    private final TourImageRepository tourImageRepository;

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

    public Page<TourResponse> getAllTour(PageRequest pageRequest) {

        return tourRepository.findAll(pageRequest).map(tourModel -> {
            return TourResponse.builder()
                    .tourName(tourModel.getTourName())
                    .tourType(tourModel.getTourType())
                    .days(tourModel.getDays())
                    .departureLocation(tourModel.getDepartureLocation())
                    .destination(tourModel.getDestination())
                    .price(tourModel.getPrice())
                    .startDate(tourModel.getStartDate())
                    .status(tourModel.getStatus())
                    .build();
        })
                ;
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
        Optional<TourModel> optionalTourModel = tourRepository.findById(tourId);
        optionalTourModel.ifPresent(tourRepository::delete);
        tourRepository.deleteById(tourId);
    }

    @Override
    public TourImageModel createTourImage(int tourId, TourImageDTO tourImageDTO) throws Exception {
        TourModel existingTourModel = tourRepository.findById(tourId)
                .orElseThrow(() -> new Exception("Cannot find category with id: " + tourImageDTO.getTourId())
                );
        TourImageModel newTourImage = TourImageModel.builder()
                .tourModel(existingTourModel)
                .imageUrl(tourImageDTO.getImgUrl())
                .build();
        int size = tourImageRepository.findByTourModel(existingTourModel).size();
        if (size >= TourImageModel.MAXIMUM_IMAGE_P_PRODUCT) {
            throw new Exception("Number of image <=" + TourImageModel.MAXIMUM_IMAGE_P_PRODUCT);
        }
        return tourImageRepository.save(newTourImage);
    }


    @Override
    public boolean existByTourName(String tourName) {
        return tourRepository.existsByTourName(tourName);
    }

}



