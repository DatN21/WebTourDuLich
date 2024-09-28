package com.dulich.toudulich.Controller;

import com.dulich.toudulich.DTO.TourDTO;
import com.dulich.toudulich.Model.TourModel;
import com.dulich.toudulich.Service.TourService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tours")
public class TourController {
    private final TourService tourService;

    @PostMapping("")
    public ResponseEntity<?> createTours(
            @Valid @RequestBody TourDTO tourDTO,
            BindingResult result)
    {
        if(result.hasErrors()){
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        tourService.createTour(tourDTO);
        return ResponseEntity.ok("Create tour successfully");
    }

    @GetMapping("")
    public ResponseEntity<List<TourModel>> getAllTour(
            @RequestParam("page") int page,
            @RequestParam("limit") int limit
    ){
        List<TourModel> tours = tourService.getAllTour();
        return ResponseEntity.ok(tours);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTourById(@PathVariable("id") String tourId){
        return ResponseEntity.ok("Tour with ID:" + tourId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTours(
            @PathVariable int id,
            @Valid @RequestBody TourDTO tourDTO)
    {
        tourService.updateTour(id, tourDTO);
        return ResponseEntity.ok("Update tour successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTours(@PathVariable int id){
        tourService.deleteTour(id);
        return ResponseEntity.ok("Delete tour with id = " + id + " successfully");
    }


}
