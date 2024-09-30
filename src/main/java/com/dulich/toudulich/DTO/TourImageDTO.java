package com.dulich.toudulich.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourImageDTO {
    @JsonProperty("tour_id")
    @Min(value = 1, message = "Product id must be >0")
    private Integer tourId ;

    @Size(min = 5,max = 200,message = "Image's name")
    @JsonProperty("image_url")
    private String imgUrl ;
}
