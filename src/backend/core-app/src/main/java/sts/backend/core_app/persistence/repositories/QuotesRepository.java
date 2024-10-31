package ies.lab.quotes.quotes.repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ies.lab.quotes.quotes.entities.Quote;

@Repository
public interface QuotesRepository extends JpaRepository<Quote, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM quotes ORDER BY RANDOM() LIMIT 1")
    Quote getRandomQuote();

    Set<Quote> findByMovieId(Long movieId);

}
