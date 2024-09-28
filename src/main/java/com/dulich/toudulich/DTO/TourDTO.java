package com.dulich.toudulich.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourDTO {

    @NotEmpty(message = "Tour name can't be empty")
    @JsonProperty("tour_name")
    String tourName;

    @NotNull(message = "Tour name can't be empty")
    int days;

    @NotNull(message = "StartDate can't be empty")
    @JsonProperty("start_date")
    Date startDate;

    @NotEmpty(message = "Destination can't be empty")
    String destination;

    @JsonProperty("tour_type")
    String tourType;

    @NotEmpty(message = "Departure location can't be empty")
    @JsonProperty("departure_location")
    String departureLocation;

    String status;

    @NotNull(message = "Price can't be empty")
    float price;


}

