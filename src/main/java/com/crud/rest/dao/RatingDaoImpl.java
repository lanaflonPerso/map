/**
 * 
 */
package com.crud.rest.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.crud.rest.beans.Movie;
import com.crud.rest.beans.Rating;
import com.crud.rest.beans.UserFavList;

/**
 * @author Archana
 *
 */
public class RatingDaoImpl implements RatingDao {

	
	@Autowired
	private SessionFactory sessionFactory;

	//setter for sessionFactory
	public void setsessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public List<Movie> findRatingByUserid(int userid) {
		// TODO Auto-generated method stub
		List<Movie> userRatedMovie = new ArrayList<Movie>();
		Session session = sessionFactory.openSession();
		//userFavList = session.createQuery("From com.crud.rest.beans.UserFavList where type='F' and userid="+userid+"").list();
		
		String sql = "select distinct  m.movieid,m.moviename,m.genres,m.overview,m.belongs_to_collection,m.original_language,m.popularity,m.poster_path,m.productionID,m.release_date,m.runtime,m.vote_average,m.vote_count,r.rating from Movie m,Rating r where m.movieid=r.movieid and r.userid=:userid";
	     SQLQuery query = session.createSQLQuery(sql);
	     query.setParameter("userid", userid);
	     query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	     userRatedMovie = query.setMaxResults(6).list();
	     session.close();
	 	return userRatedMovie;
	}

	
	@Override
	public List<Movie> findRatingByMovieId(int movieid) {
		// TODO Auto-generated method stub
		List<Movie> avgRatingofMovie = new ArrayList<Movie>();
		Session session = sessionFactory.openSession();
		//userFavList = session.createQuery("From com.crud.rest.beans.UserFavList where type='F' and userid="+userid+"").list();
		
		String sql = "select  m.movieid,m.moviename,m.genres,m.overview,m.belongs_to_collection,m.original_language,m.popularity,m.poster_path,m.productionID,m.release_date,m.runtime,m.vote_average,m.vote_count,ROUND(avg(r.rating),2) as AvgRating from Movie m,Rating r where m.movieid=r.movieid and r.movieid=:movieid group by m.MOVIEID,m.moviename,m.genres,m.overview,m.poster_path,m.original_language";
	     SQLQuery query = session.createSQLQuery(sql);
	     query.setParameter("movieid", movieid);
	     query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	     avgRatingofMovie = query.setMaxResults(6).list();
	     session.close();
	 	return avgRatingofMovie;
	}

	
	@Override
	public void saveRating(Rating rating) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (rating != null) {
			try {
				System.out.println(rating.getUserid()+"\t"+rating.getRating()+"\t"+rating.getMovieid());
				session.save(rating);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}
	}

	
	@Override
	public void updateRating(Rating rating) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (rating != null) {
			try {
				session.update(rating);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}
	}

	
	@Override
	public void deleteRating(int userid, int movieid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		//Rating rating = new Rating();
		try {
			
			String hql = "delete from rating where userid= :userid AND movieid= :movieid";
			 Query query = session.createQuery(hql);
			  query.setInteger("userid", userid);
			  query.setInteger("movieid", movieid);
			  System.out.println(query.executeUpdate());
			  // your code end
			/*rating = (Rating) session.get(Rating.class, userid);
			session.delete(rating);*/
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
	}

	
	@Override
	public List<Movie> topRatedMovies() {
		// TODO Auto-generated method stub
		List<Movie> topRatedMovies = new ArrayList<Movie>();
		Session session = sessionFactory.openSession();
		//userFavList = session.createQuery("From com.crud.rest.beans.UserFavList where type='F' and userid="+userid+"").list();
		
		String sql = "select distinct  m.movieid,m.moviename,m.genres,m.overview,m.belongs_to_collection,m.original_language,m.popularity,m.poster_path,m.productionID,m.release_date,m.runtime,m.vote_average,m.vote_count,ROUND(avg(r.rating),2) as AvgRating from prophet2018spring.Movie m,prophet2018spring.Rating r where m.movieid=r.movieid group by  m.movieid,m.moviename,m.genres,m.overview,m.belongs_to_collection,m.original_language,m.popularity,m.poster_path,m.productionID,m.release_date,m.runtime,m.vote_average,m.vote_count order by AvgRating desc";
	     SQLQuery query = session.createSQLQuery(sql);
	     
	     query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	     topRatedMovies = query.setMaxResults(6).list();
	     session.close();
	 	return topRatedMovies;
	}

	@Override
	public Rating getRatingData(Rating rating){
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Rating data = new Rating();
		String hql = "from com.crud.rest.beans.Rating where userid=:userid and movieid=:movieid and rating = :rating";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("userid", rating.getUserid());
			query.setParameter("movieid", rating.getMovieid());
			query.setParameter("rating", rating.getRating());
			data = (Rating) query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return data;
	}
	
	@Override
	public boolean isRatingExist(Rating rating) {
		// TODO Auto-generated method stub
		return getRatingData(rating)!=null;
	}


	/* (non-Javadoc)
	 * @see com.crud.rest.dao.RatingDao#findRatingByIds(int, int)
	 */
	@Override
	public Rating findRatingByIds(int userid, int movieid) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Rating data = new Rating();
		String hql = "from com.crud.rest.beans.Rating where userid=:userid and movieid=:movieid";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("userid", userid);
			query.setParameter("movieid", movieid);
			data = (Rating) query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return data;
	}


	
	@Override
	public List<Movie> cosineSimilarity(int userid)  {
		// TODO Auto-generated method recommendationBasedonRatingstub
		List<Integer> userids = new ArrayList<Integer>();
		userids =listofUniqueUserid();
		int numofUsers = userids.size();
		
		List<Integer> movieids = new ArrayList<Integer>();
		movieids =listofUniqueMovieid();
		int numofMovies = movieids.size();
		
		
		Float[][] ratingMatrix = new Float[numofUsers][numofMovies];
		ratingMatrix = generateMatrix(userids,movieids);
		for(int i=0;i<numofUsers;i++){
			for(int j=0;j<numofMovies;j++){
				System.out.print(ratingMatrix[i][j]+"\t\t");
			}
			System.out.println();
		}
		
		int userPosition = getUserPositioninMatrix(userid,userids);
		double numerator=0,denominator1=0,denominator2=0,cosine=0;
		List<Double> cosineValues=new ArrayList<Double>();
		if(userPosition != -1){
			for(int i =0;i<userids.size();i++){
				numerator=0;
				denominator1=0;
				denominator2=cosine=0;
				for(int j = 0;j<movieids.size();j++){
					if((ratingMatrix[userPosition][j] !=0)&&(ratingMatrix[i][j]!=0)&&(userPosition!=i)){
						
						numerator = numerator + (ratingMatrix[userPosition][j] *ratingMatrix[i][j]);
						//System.out.println(ratingMatrix[userPosition][j] +"\t\t"+ratingMatrix[i][j]);
					}
					denominator1=denominator1+(ratingMatrix[userPosition][j]*ratingMatrix[userPosition][j]);
					denominator2=denominator2+(ratingMatrix[i][j]*ratingMatrix[i][j]);
				}
				cosine = (numerator / (Math.sqrt(denominator1)*Math.sqrt(denominator2)));
				System.out.println(cosine);
				cosineValues.add(cosine);
			}
			
		}
		double max=0;
		int cosinePosition=-1;
		for(int k=0;k<cosineValues.size();k++){
			if(cosineValues.get(k)>max){
				max=cosineValues.get(k);
				cosinePosition=k;
			}
		}
		
		System.out.println(cosinePosition);
		List<Movie> recommendedMovies = new ArrayList<Movie>();
		if(cosinePosition != -1){
		recommendedMovies = getRecommendedMovies(cosinePosition,userid,userids);
		}
		double secondLargest=0;
		if(recommendedMovies.isEmpty()){
			System.out.println("Max values-->"+max);
			cosinePosition=-1;
			for(int k=0;k<cosineValues.size();k++){
				if(cosineValues.get(k)>max){
					secondLargest=max;
					max=cosineValues.get(k);
					
				}
				if(cosineValues.get(k) > secondLargest && cosineValues.get(k) != max) {
				    secondLargest = cosineValues.get(k);
				    cosinePosition=k;
				  }
				
			}
			System.out.println("Second largest value -->"+secondLargest+"\t Cosine Position --->"+cosinePosition);
			if(cosinePosition != -1){
				recommendedMovies = getRecommendedMovies(cosinePosition,userid,userids);
				}
			
		}
		
		
		return recommendedMovies;
	}

	@Override
	public List<Integer> listofUniqueUserid() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		List<Integer> userids=new ArrayList<Integer>();
		String sql = "select distinct userid from Rating order by userid";
		try {
			Query query = session.createSQLQuery(sql);
			userids= query.list();
			session.close();
			  
			
		} catch (Exception e) {
			session.close();
		}
		return userids;
	}


	@Override
	public List<Integer> listofUniqueMovieid() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		List<Integer> movieids=new ArrayList<Integer>();
		String sql = "select distinct Movieid from prophet2018spring.Rating order by Movieid";
		try {
			Query query = session.createSQLQuery(sql);
			movieids= query.list();
			
			session.close();
			
		} catch (Exception e) {
			session.close();
		}
		return movieids;
	}


	
	@Override
	public Float[][] generateMatrix(List<Integer> users, List<Integer> Movies) {
		// TODO Auto-generated method stub
		Float[][] matrix = new Float[users.size()][Movies.size()];
		
		for(int i=0;i<users.size();i++ ){
			
			for(int j =0;j<Movies.size();j++){
				Session session = sessionFactory.openSession();
				String sql = "select rating from Rating where userid ="+users.get(i)+" and movieid ="+Movies.get(j);
				Query query = session.createQuery(sql);
				if(query.uniqueResult() != null){
				matrix[i][j]= (Float) query.uniqueResult();
				}else{
					matrix[i][j] = (float) 0.0;
				}
				session.close();
			}
		}
		
		return matrix;
	}


	
	@Override
	public int getUserPositioninMatrix(int userid, List<Integer> userids) {
		// TODO Auto-generated method stub
		int pos =-1;
		
		for(int i=0;i<userids.size();i++){
			System.out.println(userids.get(i));
			if(userid == userids.get(i)){
				pos = i;
			}
		}
		System.out.println("User position in list-->"+pos);
		return pos;
	}


	/* (non-Javadoc)
	 * @see com.crud.rest.dao.RatingDao#getRecommendedMovies(int, java.util.List)
	 */
	@Override
	public List<Movie> getRecommendedMovies(int cosinePosition,int id,List<Integer> userids) {
		// TODO Auto-generated method stub
		List<Movie> recommendedMovie = new ArrayList<Movie>();
		Integer userid = userids.get(cosinePosition);
		Session session = sessionFactory.openSession();
		//userFavList = session.createQuery("From com.crud.rest.beans.UserFavList where type='F' and userid="+userid+"").list();
		List<Integer> movies = new ArrayList<Integer>();
		String sql = " select Movieid from prophet2018spring.Rating where userid = "+userid+" and movieid NOT IN(select Movieid from prophet2018spring.Rating where userid = "+id+")";
		SQLQuery query = session.createSQLQuery(sql);
		
		movies = query.list();
		System.out.println(movies.size());
		
		if(movies.size()>0){
		String sql1 = "select  m.movieid,m.moviename,m.genres,m.overview,m.belongs_to_collection,m.original_language,m.popularity,m.poster_path,m.productionID,m.release_date,m.runtime,m.vote_average,m.vote_count from prophet2018spring.Movie m where m.movieid = ";
		for(int i =0;i<movies.size()-1;i++){
			sql1 += movies.get(i)+" or movieid = ";
		}
		sql1+= movies.get(movies.size()-1);
		//Session session1 = sessionFactory.openSession();
	     SQLQuery query1 = session.createSQLQuery(sql1);
	   
	     query1.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	     recommendedMovie = query1.list();
	     
		}
		session.close();
		return recommendedMovie;
	}

	
}
