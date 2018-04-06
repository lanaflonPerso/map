package com.crud.rest.dao;

import java.util.List;

import com.crud.rest.beans.Collection;



/**
 * @author Archana
 *
 */
public interface CollectionDao {

	Collection findById(int id);

	Collection findByName(String name);

	void saveCollection(Collection collection);

	List<Collection> findAllCollection();

	boolean isCollectionExist(Collection collection);
	
	List<Collection> getMovieDataFromCollection(int collectionID);
	
}
