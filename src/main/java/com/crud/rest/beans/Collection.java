package com.crud.rest.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @author Archana
 *
 */
@Entity
@Table(name="Collection")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Collection {
	
	@Id
	@Column
	private int collection_Id;
	
	@Column
	private String collection_Name;
	
	@Column
	private String collection_Posterpath;
	
	@Column
	private String collection_BackDropPath;

	public Collection() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Collection(int collection_Id, String collection_Name,
			String collection_Posterpath, String collection_BackDropPath) {
		super();
		this.collection_Id = collection_Id;
		this.collection_Name = collection_Name;
		this.collection_Posterpath = collection_Posterpath;
		this.collection_BackDropPath = collection_BackDropPath;
	}

	public int getCollection_Id() {
		return collection_Id;
	}

	public void setCollection_Id(int collection_Id) {
		this.collection_Id = collection_Id;
	}

	public String getCollection_Name() {
		return collection_Name;
	}

	public void setCollection_Name(String collection_Name) {
		this.collection_Name = collection_Name;
	}

	public String getCollection_Posterpath() {
		return collection_Posterpath;
	}

	public void setCollection_Posterpath(String collection_Posterpath) {
		this.collection_Posterpath = collection_Posterpath;
	}

	public String getCollection_BackDropPath() {
		return collection_BackDropPath;
	}

	public void setCollection_BackDropPath(String collection_BackDropPath) {
		this.collection_BackDropPath = collection_BackDropPath;
	}
	
	
	
	

}
