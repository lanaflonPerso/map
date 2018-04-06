package com.crud.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import com.crud.rest.beans.Movie;
import com.crud.rest.dao.MovieDao;


/**
 * @author Archana
 *
 */
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;
	
	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}
	
	
	@Override
	public Movie findById(int id) {
		// TODO Auto-generated method stub
		return movieDao.findById(id);
	}

	@Override
	public Movie findByName(String name) {
		// TODO Auto-generated method stub
		return movieDao.findByName(name);
	}

	@Override
	public void saveMovie(Movie movie) {
		// TODO Auto-generated method stub
		movieDao.saveMovie(movie);
	}

	@Override
	public void updateMovie(Movie movie) {
		// TODO Auto-generated method stub
		movieDao.updateMovie(movie);
	}

	@Override
	public void deleteMovieById(int id) {
		// TODO Auto-generated method stub
		movieDao.deleteMovieById(id);
	}

	@Override
	public List<Movie> findAllMovie() {
		// TODO Auto-generated method stub
		return movieDao.findAllMovie();
	}

	@Override
	public void deleteAllMovie() {
		// TODO Auto-generated method stub
		movieDao.deleteAllMovie();
	}

	@Override
	public boolean isMovieExist(Movie movie) {
		// TODO Auto-generated method stub
		return movieDao.isMovieExist(movie);
	}
	
	@Override
	public List<Movie> top6Movie(String genre){
		return movieDao.top6Movie(genre);
	}

	@Override
	public List<Movie> movieBasedonGenre(String genre){
		return movieDao.movieBasedonGenre(genre);
	}
	
	@Override
	public List<Movie> recentMovies(){
		return movieDao.recentMovies();
	}


	@Override
	public List<Movie> movieBasedonLanguague(String language) {
		// TODO Auto-generated method stub
		return movieDao.movieBasedonLanguague(language);
	}


	/* (non-Javadoc)
	 * @see com.crud.rest.service.MovieService#movieBasedonPopularity()
	 */
	@Override
	public List<Movie> movieBasedonPopularity() {
		// TODO Auto-generated method stub
		return movieDao.movieBasedonPopularity();
	}


	/* (non-Javadoc)
	 * @see com.crud.rest.service.MovieService#getMovieid()
	 */
	@Override
	public int getMovieid() {
		// TODO Auto-generated method stub
		return movieDao.getMovieid();
	}
	
	
}
