package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public void addMovie(Movie movie){
        movieRepository.addedMovie(movie);
    }
    public void addDirector(Director director){
        movieRepository.addedDirector(director);
    }
    public void pairMovieDirector(String movie,String director){
        movieRepository.pairedMovieDirector(movie, director);
    }
    public Movie findMovie(String name){
        Movie movie = movieRepository.moviesName(name);
        return movie;
    }
    public Director findDirector(String directorName){
        Director director = movieRepository.directorName(directorName);
        return director;
    }
    public List<String> findMovieByDirectorName(String name){
        return movieRepository.findMovieByDirector(name);
    }
    public List<String> findAllMovie(){
        return movieRepository.findAllTheMovies();
    }

    public void deleteDirectorByName(String director) {
        movieRepository.deleteDirectoryByName(director);
    }
}
