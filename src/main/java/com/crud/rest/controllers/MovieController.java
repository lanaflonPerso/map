package com.crud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.crud.rest.beans.Movie;
import com.crud.rest.service.MovieService;


/**
 * @author Archana
 *
 */
@RestController
public class MovieController {
	
	@Autowired
	private MovieService movieService;

	//setter for MovieService
	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}

	// Add Movie
	@RequestMapping(value = "/movie/new", method = RequestMethod.POST)
	public ResponseEntity<Void> addMovie(@RequestBody Movie movie, UriComponentsBuilder ucb) {
		if (movieService.isMovieExist(movie)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {

			movieService.saveMovie(movie);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucb.path("/movie/{id}").buildAndExpand(movie.getMovieId()).toUri());
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}

	// Get Single Movie
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Movie> getMovie(@PathVariable("id") int id) {

		Movie movie = movieService.findById(id);
		if (movie == null) {
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	}

	// Get All Movie
	@RequestMapping(value = "/movies", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> listAllMovie() {
		List<Movie> movies = movieService.findAllMovie();
		if (movies.isEmpty()) {
			return new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}

	// Update Movie
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Movie> updateMovie(@PathVariable("id") int id, @RequestBody Movie movie) {

		Movie us = movieService.findById(id);

		if (movie == null) {
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
		}

		us.setMovieName(movie.getMovieName());
		us.setBelongs_to_collection(movie.getBelongs_to_collection());
		us.setGenres(movie.getGenres());
		us.setOriginal_language(movie.getOriginal_language());
		us.setOverview(movie.getOverview());
		us.setPopularity(movie.getPopularity());
		us.setPoster_path(movie.getPoster_path());
		us.setProductionID(movie.getProductionID());
		us.setRelease_date(movie.getRelease_date());
		us.setRuntime(movie.getRuntime());
		us.setVote_average(movie.getVote_average());
		us.setVote_count(movie.getVote_count());
				
		movieService.updateMovie(us);
		return new ResponseEntity<Movie>(us, HttpStatus.OK);
	}

	// Delete Movie
	@RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Movie> deleteMovie(@PathVariable("id") int id) {

		Movie movie = movieService.findById(id);
		if (movie == null) {
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
		}

		movieService.deleteMovieById(id);
		return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
	}

	// Delete All Movie
	@RequestMapping(value = "/movie/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<Movie> deleteAllMovie() {

		movieService.deleteAllMovie();
		return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/movie/top6/{genre}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> listTopMovieByGenre(@PathVariable("genre") String genre) {
		List<Movie> movies = movieService.top6Movie(genre);
		if (movies.isEmpty()) {
			return new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/movie/genre/{genre}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> listMovieByGenre(@PathVariable("genre") String genre) {
		List<Movie> movies = movieService.movieBasedonGenre(genre);
		if (movies.isEmpty()) {
			return new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/movie/popular", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> listMovieByPopularity() {
		List<Movie> movies = movieService.movieBasedonPopularity();
		if (movies.isEmpty()) {
			return new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/movie/recent", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> recentMovies() {
		List<Movie> movies = movieService.recentMovies();
		if (movies.isEmpty()) {
			return new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/movie/language/{lang}", method = RequestMethod.GET)
	public ResponseEntity<List<Movie>> listMovieByLanguage(@PathVariable("lang") String lang) {
		List<Movie> movies = movieService.movieBasedonLanguague(lang);
		if (movies.isEmpty()) {
			return new ResponseEntity<List<Movie>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/movieid", method = RequestMethod.GET)
	public ResponseEntity<Integer> getMovieId() {
		int movieID = movieService.getMovieid();
		if (movieID == 0) {
			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Integer>(movieID, HttpStatus.OK);
	}
}

