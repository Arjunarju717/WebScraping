package com.webscraping.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.webscraping.dto.EventDetailsDTO;
import com.webscraping.dto.ResponseDTO;

public interface EventDetailsService {
	
	public ResponseEntity<ResponseDTO> eventDataScrapping();

	public List<EventDetailsDTO> getEventDetails();

}
