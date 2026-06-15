package com.example.learning.LLDPractice.BookMyShow;

import lombok.Data;

import java.time.Duration;

@Data
public class Movie {
    private final String movieId;
    private final String title;
    private final Duration duration;

    public Movie(String movieId, String title, Duration duration) {
        this.movieId = movieId;
        this.title = title;
        this.duration = duration;
    }
}
