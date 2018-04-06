/**
 * 
 */
package com.crud.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.rest.beans.Movie;
import com.crud.rest.beans.Rating;
import com.crud.rest.dao.RatingDao;

/**
 * @author Archana
 *
 */
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingDao ratingDao;
	
	
	/**
	 * @param ratingDao the ratingDao to set
	 */
	public void setRatingDao(RatingDao ratingDao) {
		this.ratingDao = ratingDao;
	}


	@Override
	public List<Movie> findRatingByUserid(int userid) {
		// TODO Auto-generated method stub
		return ratingDao.findRatingByUserid(userid);
	}

	
	@Override
	public List<Movie> findRatingByMovieId(int movieid) {
		// TODO Auto-generated method stub
		return ratingDao.findRatingByMovieId(movieid);
	}

	@Override
	public void saveRating(Rating rating) {
		// TODO Auto-generated method stub
		ratingDao.saveRating(rating);
	}

	
	@Override
	public void updateRating(Rating rating) {
		// TODO Auto-generated method stub
		ratingDao.updateRating(rating);
	}

	
	@Override
	public void deleteRating(int userid, int movieid) {
		// TODO Auto-generated method stub
		ratingDao.deleteRating(userid, movieid);
	}

	@Override
	public List<Movie> topRatedMovies() {
		// TODO Auto-generated method stub
		return ratingDao.topRatedMovies();
	}


	/* (non-Javadoc)
	 * @see com.crud.rest.service.RatingService#isRatingExist(com.crud.rest.beans.Rating)
	 */
	@Override
	public boolean isRatingExist(Rating rating) {
		// TODO Auto-generated method stub
		return ratingDao.isRatingExist(rating);
	}


	/* (non-Javadoc)
	 * @see com.crud.rest.service.RatingService#getRatingData(com.crud.rest.beans.Rating)
	 */
	@Override
	public Rating getRatingData(Rating rating) {
		// TODO Auto-generated method stub
		return ratingDao.getRatingData(rating);
	}


	/* (non-Javadoc)
	 * @see com.crud.rest.service.RatingService#findRatingByIds(int, int)
	 */
	@Override
	public Rating findRatingByIds(int userid, int movieid) {
		// TODO Auto-generated method stub
		return ratingDao.findRatingByIds(userid, movieid);
	}


	
	@Override
	public List<Movie> cosineSimilarity(int userid) {
		// TODO Auto-generated method stub
		return ratingDao.cosineSimilarity(userid);
	}


	/* (non-Javadoc)
	 * @see com.crud.rest.service.RatingService#listofUniqueUserid()
	 */
	@Override
	public List<Integer> listofUniqueUserid() {
		// TODO Auto-generated method stub
		return ratingDao.listofUniqueUserid();
	}


	/* (non-Javadoc)
	 * @see com.crud.rest.service.RatingService#listofUniqueMovieid()
	 */
	@Override
	public List<Integer> listofUniqueMovieid() {
		// TODO Auto-generated method stub
		return ratingDao.listofUniqueMovieid();
	}


	/* (non-Javadoc)
	 * @see com.crud.rest.service.RatingService#generateMatrix(java.util.List, java.util.List)
	 */
	@Override
	public Float[][] generateMatrix(List<Integer> users, List<Integer> Movies) {
		// TODO Auto-generated method stub
		return ratingDao.generateMatrix(users, Movies);
	}


	/* (non-Javadoc)
	 * @see com.crud.rest.service.RatingService#getUserPositioninMatrix(int, java.util.List)
	 */
	@Override
	public int getUserPositioninMatrix(int userid, List<Integer> userids) {
		// TODO Auto-generated method stub
		return ratingDao.getUserPositioninMatrix(userid, userids);
	}


	/* (non-Javadoc)
	 * @see com.crud.rest.service.RatingService#getRecommendedMovies(int, java.util.List)
	 */
	@Override
	public List<Movie> getRecommendedMovies(int cosinePosition,int userid,
			List<Integer> userids) {
		// TODO Auto-generated method stub
		return ratingDao.getRecommendedMovies(cosinePosition, userid,userids);
	}


}
