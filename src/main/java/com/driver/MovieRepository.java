package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class MovieRepository {
    private HashMap<String, Movie> movieList;
    private HashMap<String, Director> directorList;
    private HashMap<String, List<String>> directorMovieList;

    public MovieRepository(HashMap<String, Movie> movieList, HashMap<String, Director> directorList, HashMap<String, List<String>> directorMovieList) {
        this.movieList = movieList;
        this.directorList = directorList;
        this.directorMovieList = directorMovieList;
    }

    public void addedMovie(Movie movie){
        movieList.put(movie.getName(), movie);
    }
    public void addedDirector(Director director){
        directorList.put(director.getName(), director);
    }
    public void pairedMovieDirector(String movie,String director){
        List<String> listOfMovies = new ArrayList<>();
        if(directorMovieList.containsKey(director)){
            listOfMovies = directorMovieList.get(director);
        }
        listOfMovies.add(movie);
        directorMovieList.put(director, listOfMovies);
    }
    public Movie moviesName(String name){
        return movieList.get(name);
    }
    public Director directorName(String directorName){
        return directorList.get(directorName);
    }
    public List<String> findMovieByDirector(String name){
        List<String>  movie = new ArrayList<>();
        if(directorMovieList.containsKey(name)){
            movie = directorMovieList.get(name);
        }
        return movie;
    }
    public List<String> findAllTheMovies(){
        return new ArrayList<>(movieList.keySet());
    }

    public void deleteDirectoryByName(String director) {
        List<String> movies = new ArrayList<>();
        if(directorMovieList.containsKey(director)){
            movies = directorMovieList.get(director);
        }
        for(String mov : movies){
            if(movieList.containsKey(mov)){
                movieList.remove(mov);
            }
        }
        if(directorList.containsKey(director)){
            directorList.remove(director);
        }
    }
    public void deleteAllDirectors(){
        HashSet<String> movieCollection = new HashSet<>();
        for(String director: directorMovieList.keySet()){
            for(String movies : directorMovieList.get(director)){
                movieCollection.add(movies);
            }
        }
        for(String movie: movieCollection){
            movieList.remove(movie);
        }
    }
}
