package com.dulich.toudulich.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tours")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "tour_name", nullable = false, length = 255)
    String tourName;

    @Column(name = "days", nullable = false)
    int days;

    @Column(name = "start_date", nullable = false)
    Date startDate;

    @Column(name = "destination", nullable = false, length = 255)
    String destination;

    @Column(name = "tour_type", length = 50)
    String tourType;

    @Column(name = "departure_location", nullable = false, length = 255)
    String departureLocation;

    @Column(name = "status", length = 50)
    String status;

    @Column(name = "price", nullable = false)
    float price;
}
