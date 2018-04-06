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


import com.crud.rest.beans.Collection;
import com.crud.rest.beans.Movie;
import com.crud.rest.service.CollectionService;

/**
 * @author Archana
 *
 */
@RestController
public class CollectionController {

	@Autowired
	private CollectionService collectionService;

	public void setCollectionService(CollectionService collectionService) {
		this.collectionService = collectionService;
	}
	
	@RequestMapping(value = "/collection/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection> getCollectionByID(@PathVariable("id") int id) {

		Collection collection = collectionService.findById(id);
		if (collection == null) {
			return new ResponseEntity<Collection>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection>(collection, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/collection/new", method = RequestMethod.POST)
	public ResponseEntity<Void> addCollection(@RequestBody Collection collection, UriComponentsBuilder ucb) {
		if (collectionService.isCollectionExist(collection)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else {

			collectionService.saveCollection(collection);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucb.path("/collection/{id}").buildAndExpand(collection.getCollection_Id()).toUri());
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value = "/collections", method = RequestMethod.GET)
	public ResponseEntity<List<Collection>> listAllCollection() {
		List<Collection> collections = collectionService.findAllCollection();
		if (collections.isEmpty()) {
			return new ResponseEntity<List<Collection>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Collection>>(collections, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/collection/movies/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Collection>> listMovieByCollection(@PathVariable("id") int id) {
		List<Collection> collections = collectionService.getMovieDataFromCollection(id);
		if (collections.isEmpty()) {
			return new ResponseEntity<List<Collection>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Collection>>(collections, HttpStatus.OK);
	}
}
