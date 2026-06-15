package com.example.learning.LLDPractice.BookMyShow;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShowSeat {
    private final Show show;
    private final Seat seat;
    private SeatStatus seatStatus;
}
