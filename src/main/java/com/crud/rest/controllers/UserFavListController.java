package com.crud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


import com.crud.rest.beans.Movie;
import com.crud.rest.beans.UserFavList;
import com.crud.rest.service.UserFavListService;


/**
 * @author Archana
 *
 */
@RestController
public class UserFavListController {
	
	@Autowired
	private UserFavListService userFavListService;

	public void setUserFavListService(UserFavListService userFavListService) {
		this.userFavListService = userFavListService;
	}
	
	@RequestMapping(value = "/userfav/{userid}", method = RequestMethod.GET)
	public ResponseEntity<List<UserFavList>> listUserFavoriteMovie(@PathVariable("userid") int userid) {
		List<UserFavList> userFavList = userFavListService.getFavList(userid);
		if (userFavList.isEmpty()) {
			return new ResponseEntity<List<UserFavList>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserFavList>>(userFavList, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/userwat/{userid}", method = RequestMethod.GET)
	public ResponseEntity<List<UserFavList>> listUserWatchedMovie(@PathVariable("userid") int userid) {
		List<UserFavList> userFavList = userFavListService.getWatchedList(userid);
		if (userFavList.isEmpty()) {
			return new ResponseEntity<List<UserFavList>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<UserFavList>>(userFavList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/userfav/new", method = RequestMethod.POST)
	public ResponseEntity<Void> addUserPreference(@RequestBody UserFavList userFav, UriComponentsBuilder ucb) {
		
			userFavListService.saveUserPreference(userFav);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucb.path("/userfav/{id}").buildAndExpand(userFav.getUserid()+"/"+userFav.getMovieid()).toUri());
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		
	}
	
	// Delete Movie
		@RequestMapping(value = "/userfav/{userid}/{movieid}/{type}", method = RequestMethod.DELETE)
		public ResponseEntity<Movie> deleteMovie(@PathVariable("userid")int userid,@PathVariable("movieid") int movieid,@PathVariable("type") String type) {
			UserFavList userFavList = new UserFavList(userid, movieid, type);
			
			
			if (userFavList == null) {
				return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
			}
			userFavListService.deleteUserPreference(userFavList);
			return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
		}
	
}
