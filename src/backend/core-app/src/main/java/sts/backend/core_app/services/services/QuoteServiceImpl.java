// package ies.lab.quotes.quotes.services;

// import java.util.Set;
// import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;

// import ies.lab.quotes.quotes.entities.Quote;
// import ies.lab.quotes.quotes.exceptions.ResourceNotFoundException;
// import ies.lab.quotes.quotes.repositories.QuotesRepository;

// @Service
// public class QuoteServiceImpl implements QuoteService {

//     private QuotesRepository quotesRepository;

//     public QuoteServiceImpl(QuotesRepository quotesRepository) {
//         this.quotesRepository = quotesRepository;
//     }

//     @Override
//     public Quote createQuote(Quote quote) {
//         return quotesRepository.save(quote);
//     }

//     @Override
//     public Quote getQuoteById(Long quoteId) throws ResourceNotFoundException {
//         Quote quote = quotesRepository.findById(quoteId)
//                         .orElseThrow(() -> new ResourceNotFoundException("Quote with ID " + quoteId + " not found"));
//         return quote;
//     }

//     @Override
//     public Quote updateQuote(Quote quote) {
//         Quote existingQuote = quotesRepository.findById(quote.getId()).get();
//         existingQuote.setQuote(quote.getQuote());
//         existingQuote.setMovie(quote.getMovie());
//         Quote updateQuote = quotesRepository.save(existingQuote);
//         return updateQuote;
//     }

//     @Override
//     public void deleteQuote(Long quoteId) {
//         quotesRepository.deleteById(quoteId);
//     }

//     @Override
//     public Set<Quote> getAllQuotes() {
//         return quotesRepository.findAll().stream().collect(Collectors.toSet());
//     }

//     @Override
//     public Quote getRandomQuote() {
//         return quotesRepository.getRandomQuote();
//     }

//     @Override
//     public Set<Quote> getQuotesForMovieId(Long movieId) throws ResourceNotFoundException {
//         return quotesRepository.findByMovieId(movieId);
//     }
    
// }
