// package ies.lab.quotes.quotes.services;

// import java.util.Set;
// import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;

// import ies.lab.quotes.quotes.entities.Movie;
// import ies.lab.quotes.quotes.exceptions.ResourceNotFoundException;
// import ies.lab.quotes.quotes.repositories.MovieRepository;

// @Service
// public class MovieServiceImpl implements MovieService {

//     private MovieRepository movieRepository;

//     public MovieServiceImpl(MovieRepository movieRepository) {
//         this.movieRepository = movieRepository;
//     }

//     @Override
//     public Movie createMovie(Movie movie) {
//         return movieRepository.save(movie);
//     }

//     @Override
//     public Movie getMovieById(Long movieId) throws ResourceNotFoundException {
//         Movie movie = movieRepository.findById(movieId)
//                      .orElseThrow(() -> new ResourceNotFoundException("Movie with ID " + movieId + " not found"));
//         return movie;
//     }

//     @Override
//     public Movie updateMovie(Movie movie) {
//         Movie existingMovie = movieRepository.findById(movie.getId()).get();
//         existingMovie.setTitle(movie.getTitle());
//         existingMovie.setYear(movie.getYear());
//         Movie updatedMovie = movieRepository.save(existingMovie);
//         return updatedMovie;
//     }

//     @Override
//     public void deleteMovie(Long movieId) {
//         movieRepository.deleteById(movieId);
//     }

//     @Override
//     public Set<Movie> getAllMovies() {
//         return movieRepository.findAll().stream().collect(Collectors.toSet());
//     }
    
// }
