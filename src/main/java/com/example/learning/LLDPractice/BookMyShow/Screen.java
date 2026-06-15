package com.example.learning.LLDPractice.BookMyShow;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Screen {
    private final String screenId;
    private final List<Seat> seats;
}
