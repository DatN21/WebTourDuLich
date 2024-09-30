package com.dulich.toudulich.responses;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListBookingResponse {
    private List<BookingResponse> bookingResponses ;
    private int totalPages ;
}
