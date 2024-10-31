package ies.lab.quotes.quotes.dto;

public class QuoteRequest {
    private String quote;
    private Long movieId;

    // Getters and setters
    public String getQuote() { return quote; }
    public void setQuote(String quote) { this.quote = quote; }

    public Long getMovieId() { return movieId; }
    public void setMovieId(Long movieId) { this.movieId = movieId; }
}