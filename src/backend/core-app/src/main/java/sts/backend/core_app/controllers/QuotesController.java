package ies.lab.quotes.quotes.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ies.lab.quotes.quotes.dto.QuoteRequest;
import ies.lab.quotes.quotes.entities.Movie;
import ies.lab.quotes.quotes.entities.Quote;
import ies.lab.quotes.quotes.exceptions.ResourceNotFoundException;
import ies.lab.quotes.quotes.services.MovieService;
import ies.lab.quotes.quotes.services.QuoteService;

import java.util.Set;

@RestController
public class QuotesController 
{ 
    public record MovieRecord(Movie movie) { }
    public record QuoteRecord(Quote quote) { }
    public record QuotesRecord(Set<Quote> contents) { }
	public record MoviesRecord(Set<Movie> moviesId) { }

    private MovieService movieService;
    private QuoteService quoteService;

    public QuotesController(MovieService movieService, QuoteService quoteService) {
        this.movieService = movieService;
        this.quoteService = quoteService;
    }

	@GetMapping("/api/movies")
	public MoviesRecord api_movies() {
        return new MoviesRecord(movieService.getAllMovies());
	}

    @GetMapping("/api/quotes")
    public QuotesRecord api_quotes(@RequestParam(value="movie", required=false) Long movieId) throws ResourceNotFoundException {
        if (movieId != null) {
            return new QuotesRecord(quoteService.getQuotesForMovieId(movieId));  
        } 

        return new QuotesRecord(quoteService.getAllQuotes());    
    }

	@GetMapping("/api/random/quote")
	public QuoteRecord api_random_quote()
	{
		return new QuoteRecord(quoteService.getRandomQuote());
	}

    @PostMapping("api/movies")
    public MovieRecord api_create_movie(@RequestBody Movie movie) {
        return new MovieRecord(movieService.createMovie(movie));
    }

    @PostMapping("api/quotes")
    public QuoteRecord api_create_quote(@RequestBody QuoteRequest quoteRequest) throws ResourceNotFoundException {
        Quote quote = new Quote();
        quote.setQuote(quoteRequest.getQuote());
        
        Movie movie = movieService.getMovieById(quoteRequest.getMovieId());
        quote.setMovie(movie);
        
        return new QuoteRecord(quoteService.createQuote(quote));
    }

}
