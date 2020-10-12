package com.webscraping.customrepository;

import java.util.List;

import com.webscraping.entity.EventDetails;

public interface CustomEventDetailsRepository {
	
	public List<EventDetails> getAllEventDetails();

}
