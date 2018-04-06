package com.crud.rest.service;

import java.util.List;

import com.crud.rest.beans.Collection;

/**
 * @author Archana
 *
 */
public interface CollectionService {
	Collection findById(int id);

	Collection findByName(String name);

	void saveCollection(Collection collection);

	List<Collection> findAllCollection();

	boolean isCollectionExist(Collection collection);
	
	List<Collection> getMovieDataFromCollection(int collectionID);

}
