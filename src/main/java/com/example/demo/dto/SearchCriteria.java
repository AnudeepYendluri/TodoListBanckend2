package com.example.demo.dto;

import lombok.Data;

@Data
public class SearchCriteria {

	
	private Integer userId;
	private Integer id;
	private String title;
	private String description;
	private boolean completed;
	
	public boolean isEmpty() {
	    return userId == null && id == null && title == null && description == null;
	}

	
}
