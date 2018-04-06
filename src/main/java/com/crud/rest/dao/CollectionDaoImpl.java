package com.crud.rest.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.crud.rest.beans.Collection;



/**
 * @author Archana
 *
 */
public class CollectionDaoImpl implements CollectionDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	//setter for sessionFactory
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Collection findById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Collection collectionLogin = new Collection();
		try {
			collectionLogin = (Collection) session.get(Collection.class, id);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return collectionLogin;
	}

	@Override
	public Collection findByName(String name) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Collection collectionLogin = new Collection();
		String hql = "from com.crud.rest.beans.Collection where firstName = ?";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, name);
			collectionLogin = (Collection) query.uniqueResult();
			transaction.commit();
			session.close();
		} catch (Exception e) {
			transaction.rollback();
			session.close();
		}
		return collectionLogin;
	}

	@Override
	public void saveCollection(Collection collection) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		if (collection != null) {
			try {
				session.save(collection);
				transaction.commit();
			} catch (Exception e) {
				transaction.rollback();
				session.close();
			}

		}
	}

	
	
	@Override
	public List<Collection> findAllCollection() {
		// TODO Auto-generated method stub
		List<Collection> collection = new ArrayList<Collection>();
		Session session = sessionFactory.openSession();
		
		String sql = "select m.movieid,m.moviename,m.genres,m.overview,m.belongs_to_collection,m.original_language,m.popularity,m.poster_path,m.productionID,m.release_date,m.runtime,m.vote_average,m.vote_count,c.collection_Id,c.collection_Name,c.collection_Posterpath,c.collection_BackDropPath from prophet2018spring.Movie m,prophet2018spring.Collection c where m.belongs_to_collection = c.collection_Id";
	     SQLQuery query = session.createSQLQuery(sql);
	     query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	     collection = query.list();
	     session.close();
		return collection;
	}
	
	@Override
	public boolean isCollectionExist(Collection collection) {
		// TODO Auto-generated method stub
		return findByName(collection.getCollection_Name())!=null;
	}
	
	@Override
	public List<Collection> getMovieDataFromCollection(int collectionID){
		List<Collection> collections = new ArrayList<Collection>();
		Session session = sessionFactory.openSession();
			
		String sql = "select  m.movieid,m.moviename,m.genres,m.overview,m.belongs_to_collection,m.original_language,m.popularity,m.poster_path,m.productionID,m.release_date,m.runtime,m.vote_average,m.vote_count,c.collection_Id,c.collection_Name,c.collection_Posterpath,c.collection_BackDropPath from prophet2018spring.Movie m,prophet2018spring.Collection c where m.belongs_to_collection = c.collection_Id and c.collection_Id= :collectionID";
	     SQLQuery query = session.createSQLQuery(sql);
	     query.setParameter("collectionID", collectionID);
	     query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	     collections = query.list();
	    // return query.list();
	     session.close();
		return collections;
	}

}
