package com.example.learning.LLDPractice.BookMyShow;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Seat {
    private final String seatId;
    private SeatStatus seatStatus;
}
