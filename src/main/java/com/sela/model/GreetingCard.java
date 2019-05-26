package com.sela.model;

import java.util.Date;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "greetingcards", type = "greetingcard")
public class GreetingCard {
	
	@Field(type = FieldType.Text)
	String id;
	
	@Field(type = FieldType.Text)
	String name;
	
	@Field(type = FieldType.Text)
	String template;
	
	@Field(type = FieldType.Date)
	Date createdOn;

	public GreetingCard() {
	}

	public GreetingCard(String id, String name, String template, Date createdOn) {
		super();
		this.id = id;
		this.name = name;
		this.template = template;
		this.createdOn = createdOn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	

}
