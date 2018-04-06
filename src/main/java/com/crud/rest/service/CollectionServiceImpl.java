package com.crud.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.rest.beans.Collection;
import com.crud.rest.dao.CollectionDao;

/**
 * @author Archana
 *
 */
public class CollectionServiceImpl implements CollectionService{
	@Autowired
	private CollectionDao collectionDao;
	
	public void setCollectionDao(CollectionDao collectionDao) {
		this.collectionDao = collectionDao;
	}


	@Override
	public Collection findById(int id) {
		// TODO Auto-generated method stub
		return collectionDao.findById(id);
	}

	@Override
	public Collection findByName(String name) {
		// TODO Auto-generated method stub
		return collectionDao.findByName(name);
	}

	@Override
	public void saveCollection(Collection collection) {
		// TODO Auto-generated method stub
		collectionDao.saveCollection(collection);
	}

	@Override
	public List<Collection> findAllCollection() {
		// TODO Auto-generated method stub
		return collectionDao.findAllCollection();
	}

	

	@Override
	public boolean isCollectionExist(Collection collection) {
		// TODO Auto-generated method stub
		return collectionDao.isCollectionExist(collection);
	}
	
	@Override
	public List<Collection> getMovieDataFromCollection(int collectionID){
		return collectionDao.getMovieDataFromCollection(collectionID);
	}

}
