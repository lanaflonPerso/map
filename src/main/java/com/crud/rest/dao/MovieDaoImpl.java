package com.crud.rest.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.crud.rest.beans.Movie;




/**
 * @author Archana
 *
 */
public class MovieDaoImpl implements MovieDao{

	@Autowired
	private SessionFactory auditsessionFactory;

	//setter for sessionFactory
	public void setsessionFactory(SessionFactory auditsessionFactory) {
		this.auditsessionFactory = auditsessionFactory;
	}


	@Override
	public Movie findById(int id) {
		// TODO Auto-generated method stub
		Session session = auditsessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Movie movie = new Movie();
		try {
			movie = (Movie) session.get(Movie.class, id);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return movie;
	}

	@Override
	public Movie findByName(String name) {
		// TODO Auto-generated method stub
		Session session = auditsessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Movie movie = new Movie();
		String hql = "from com.crud.rest.beans.Movie where movieName like ?";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, name);
			movie = (Movie) query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return movie;
	}

	@Override
	public void saveMovie(Movie movie) {
		// TODO Auto-generated method stub
		Session session = auditsessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (movie != null) {
			try {
				session.save(movie);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}

	}

	@Override
	public void updateMovie(Movie movie) {
		// TODO Auto-generated method stub
		Session session = auditsessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (movie != null) {
			try {
				session.update(movie);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}
	}

	@Override
	public void deleteMovieById(int id) {
		// TODO Auto-generated method stub
		Session session = auditsessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Movie movie = new Movie();
		try {
			movie = (Movie) session.get(Movie.class, id);
			session.delete(movie);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}

	}

	@Override
	public List<Movie> findAllMovie() {
		// TODO Auto-generated method stub
		List<Movie> movie = new ArrayList<Movie>();
		Session session = auditsessionFactory.openSession();
		movie = session.createQuery("From com.crud.rest.beans.Movie").list();
		return movie;
	}

	@Override
	public void deleteAllMovie() {
		// TODO Auto-generated method stub
		Session session = auditsessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.createQuery("delete from Movie").executeUpdate();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
	}

	@Override
	public boolean isMovieExist(Movie movie) {
		// TODO Auto-generated method stub
		return findByName(movie.getMovieName())!=null;
	}

	@Override
	public List<Movie> top6Movie(String genre){
		List<Movie> movie = new ArrayList<Movie>();
		Session session = auditsessionFactory.openSession();
		
		/*String sql = "select  m.movieid,m.moviename,m.genres,m.overview,m.belongs_to_collection,m.original_language,m.popularity,m.poster_path,m.productionID,m.release_date,m.runtime,m.vote_average,m.vote_count from Movie m WHERE genres like '%"+genre+"%'";
	     SQLQuery query = session.createSQLQuery(sql);
	     query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	     movie = query.setMaxResults(6).list();
	     session.close();*/
		
		movie = session.createQuery("From com.crud.rest.beans.Movie WHERE genres like '%"+genre+"%'").setMaxResults(6).list();
		session.close();
		return movie;
	}

	@Override
	public List<Movie> movieBasedonGenre(String genre){
		List<Movie> movie = new ArrayList<Movie>();
		Session session = auditsessionFactory.openSession();
		//movie = session.createQuery("From com.crud.rest.beans.Movie WHERE genres like '%"+genre+"%' order by rand()").list();
		String sql = "select m.movieid,m.moviename,m.belongs_to_collection,m.genres,m.original_language,m.overview,m.popularity,m.poster_path,m.productionID,m.release_date,m.runtime,m.vote_average,m.vote_count from Movie m where genres like '%"+genre+"%' order by rand()";
		SQLQuery query = session.createSQLQuery(sql);
		 query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		movie = query.setMaxResults(36).list();
		  session.close();
		return movie;
	}

	@Override
	public List<Movie> recentMovies(){
		List<Movie> movies = new ArrayList<Movie>();

		Session session = auditsessionFactory.openSession();
		movies = session.createQuery("From com.crud.rest.beans.Movie where RELEASE_DATE < current_date() order by release_date desc").setMaxResults(6).list();
		  session.close();

		return movies;

	}

	@Override
	public List<Movie> movieBasedonLanguague(String language){
		List<Movie> movies = new ArrayList<Movie>();

		Session session = auditsessionFactory.openSession();
		movies = session.createQuery("From com.crud.rest.beans.Movie where original_language ='"+language+"'").setMaxResults(6).list();
		  session.close();
		return movies;
	}


	/* (non-Javadoc)
	 * @see com.crud.rest.dao.MovieDao#movieBasedonPopularity()
	 */
	@Override
	public List<Movie> movieBasedonPopularity() {
		// TODO Auto-generated method stub
		List<Movie> movies = new ArrayList<Movie>();

		Session session = auditsessionFactory.openSession();
		movies = session.createQuery("From com.crud.rest.beans.Movie order by popularity desc").setMaxResults(6).list();
		  session.close();
		return movies;
	}


	/* (non-Javadoc)
	 * @see com.crud.rest.dao.MovieDao#getMovieid()
	 */
	@Override
	public int getMovieid() {
		// TODO Auto-generated method stub
		Session session = auditsessionFactory.openSession();
		
		int movieID=0;
		String hql = "select m.movieid from Movie m order by rand()";
		try {
			Query query = session.createSQLQuery(hql);
			query.setFirstResult(0);
			query.setMaxResults(1);
			Integer ID = (Integer) query.uniqueResult();
			
			System.out.println(ID);
			session.close();
		} catch (Exception e) {
			
			session.close();
		}
		return movieID;
	}


}
