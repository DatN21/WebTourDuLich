package com.dulich.toudulich.Service;

import com.dulich.toudulich.DTO.TourDTO;
import com.dulich.toudulich.DTO.TourImageDTO;
import com.dulich.toudulich.Model.TourImageModel;
import com.dulich.toudulich.Model.TourModel;
import com.dulich.toudulich.Repositories.TourImageRepository;
import com.dulich.toudulich.Repositories.TourRepository;
import com.dulich.toudulich.enums.Status;
import com.dulich.toudulich.enums.TourType;
import com.dulich.toudulich.responses.TourResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourService implements iTourService {
    private final TourRepository tourRepository;
    private final TourImageRepository tourImageRepository;


    @Override
    public TourModel createTour(TourDTO tourDTO) throws Exception {
        // Kiểm tra và chuyển đổi enum TourType và Status
        Status status = Status.fromString(tourDTO.getStatus());
        TourType tourType = TourType.fromString(tourDTO.getTourType());
//        try {
//            tourType = TourType.valueOf(tourDTO.getTourType());
//        } catch (IllegalArgumentException e) {
//            throw new Exception("Invalid tour type: " + tourDTO.getTourType());
//        }
//
//        try {
//            status = Status.valueOf(tourDTO.getStatus());
//        } catch (IllegalArgumentException e) {
//            throw new Exception("Invalid status: " + tourDTO.getStatus());
//        }
        TourModel newTour = TourModel.builder()
                .tourName(tourDTO.getTourName())
                .days(tourDTO.getDays())
                .startDate(tourDTO.getStartDate())
                .destination(tourDTO.getDestination())
                .tourType(tourType)
                .departureLocation(tourDTO.getDepartureLocation())
                .status(status)
                .price(tourDTO.getPrice())
//                .thubnail(tourDTO.getThumbnail())
                .description(tourDTO.getDescription())
                .content(tourDTO.getContent())
                .imageHeader(tourDTO.getImageHeader())
                .build();

        //
//        // Xử lý ảnh nếu có
//        if (tourDTO.getImageUrls() != null && !tourDTO.getImageUrls().isEmpty()) {
//            String updatedContent = savedTour.getContent();
//            for (String imageUrl : tourDTO.getImageUrls()) {
//                updatedContent += "<img src='" + imageUrl + "' alt='Tour Image'>";
//            }
//
//            // Cập nhật lại content với ảnh mới
//            savedTour.setContent(updatedContent);
//            tourRepository.save(savedTour);  // Lưu lại tour với content đã được cập nhật
//        }

        return tourRepository.save(newTour);
//        return tourRepository.save(newTour);
    }

    public Page<TourResponse> getAllTour(PageRequest pageRequest) {

        return tourRepository.findAll(pageRequest).map(tourModel -> {
            return TourResponse.builder()
                    .id(tourModel.getId())
                    .tourName(tourModel.getTourName())
                    .tourType(String.valueOf(tourModel.getTourType()))
                    .days(tourModel.getDays())
                    .departureLocation(tourModel.getDepartureLocation())
                    .destination(tourModel.getDestination())
                    .price(tourModel.getPrice())
                    .startDate(tourModel.getStartDate())
                    .status(String.valueOf(tourModel.getStatus()))
                    .description(tourModel.getDescription())
                    .content(tourModel.getContent())
                    .imageHeader(tourModel.getImageHeader())
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
        Status status = Status.fromString(tourDTO.getStatus());
        TourType tourType = TourType.fromString(tourDTO.getTourType());
        TourModel existingTour = getTourById(tourId);
        existingTour.setTourName(tourDTO.getTourName());
        existingTour.setDays(tourDTO.getDays());
        existingTour.setStartDate(tourDTO.getStartDate());
        existingTour.setDestination(tourDTO.getDestination());
        existingTour.setStatus(status);
        existingTour.setPrice(tourDTO.getPrice());
        existingTour.setTourType(tourType);
//        existingTour.setThubnail(tourDTO.getThumbnail());
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

    //Xử lý ảnh
    public List<TourImageDTO> getImagesByTourId(Integer tourId) {
        List<TourImageModel> images = tourImageRepository.findByTourModel_Id(tourId);
        return images.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // Xoá một ảnh theo ID
    public void deleteImage(Integer id) {
        tourImageRepository.deleteById(id);
    }

    // Xoá tất cả ảnh theo tourId
    public void deleteAllImagesByTourId(Integer tourId) {
        List<TourImageModel> images = tourImageRepository.findByTourModel_Id(tourId);
        tourImageRepository.deleteAll(images);
    }

    // Helper: Chuyển đổi từ entity sang DTO
    private TourImageDTO mapToDTO(TourImageModel image) {
        TourImageDTO dto = new TourImageDTO();
        dto.setId(image.getId());
        dto.setImgUrl(image.getImageUrl());
        dto.setTourId(image.getTourModel().getId());
        return dto;
    }
}



