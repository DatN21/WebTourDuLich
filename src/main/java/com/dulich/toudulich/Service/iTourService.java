package com.dulich.toudulich.Service;

import com.dulich.toudulich.DTO.TourDTO;
import com.dulich.toudulich.DTO.TourImageDTO;
import com.dulich.toudulich.Model.TourImageModel;
import com.dulich.toudulich.Model.TourModel;
import com.dulich.toudulich.responses.TourResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface iTourService {
    TourModel createTour(TourDTO tour) throws Exception;
    Page<TourResponse> getAllTour(PageRequest pageRequest);
    TourModel getTourById(int id);
    TourModel updateTour(int tourId,TourDTO tour);
    void deleteTour(int tourId);
    TourImageModel createTourImage(int id , TourImageDTO tourImageDTO) throws Exception;

    boolean existByTourName(String name);

    List<TourImageDTO> getImagesByTourId (Integer tourId) ;

    void deleteImage (Integer id) ;
    void deleteAllImagesByTourId(Integer tourId) ;

//    TourImageDTO mapToDTO(TourImageModel tourImageModel) ;

}
