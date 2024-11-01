package ies.lab.quotes.quotes.services;

import java.util.Set;

import ies.lab.quotes.quotes.entities.Quote;
import ies.lab.quotes.quotes.exceptions.ResourceNotFoundException;

public interface QuoteService {

    Quote createQuote(Quote quote);

    Quote updateQuote(Quote quote);
    
    void deleteQuote(Long quoteId);
    
    Quote getQuoteById(Long quoteId) throws ResourceNotFoundException;
    
    Set<Quote> getAllQuotes();

    Quote getRandomQuote();

    Set<Quote> getQuotesForMovieId(Long movieId) throws ResourceNotFoundException;

}
