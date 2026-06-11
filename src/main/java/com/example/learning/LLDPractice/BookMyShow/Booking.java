package com.example.learning.LLDPractice.BookMyShow;

import java.util.List;

public class Booking {
    private final String bookingId;

    private final User user;

    private final List<ShowSeat> seats;

    private BookingStatus status;

    public Booking(
            String bookingId,
            User user,
            List<ShowSeat> seats) {

        this.bookingId = bookingId;
        this.user = user;
        this.seats = seats;
        this.status = BookingStatus.CREATED;
    }
}
