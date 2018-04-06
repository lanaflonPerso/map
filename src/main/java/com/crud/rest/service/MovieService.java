package com.crud.rest.service;

import java.util.List;

import com.crud.rest.beans.Movie;

/**
 * @author Archana
 *
 */
public interface MovieService {
	
	int getMovieid();
	
	Movie findById(int id);

	Movie findByName(String name);

	void saveMovie(Movie movie);

	void updateMovie(Movie movie);

	void deleteMovieById(int id);

	List<Movie> findAllMovie();

	void deleteAllMovie();

	boolean isMovieExist(Movie movie);
	
	List<Movie> top6Movie(String genre);
	
	List<Movie> movieBasedonGenre(String genre);
	
	List<Movie> recentMovies();
	
	List<Movie> movieBasedonLanguague(String language);
	
	List<Movie> movieBasedonPopularity();

}
