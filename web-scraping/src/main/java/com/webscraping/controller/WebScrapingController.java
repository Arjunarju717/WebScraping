package com.webscraping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webscraping.dto.EventDetailsDTO;
import com.webscraping.dto.ResponseDTO;
import com.webscraping.dto.WebsiteDetailsDTO;
import com.webscraping.service.EventDetailsService;
import com.webscraping.service.WebsiteDetailsService;

@RestController
public class WebScrapingController {

	@Autowired
	private WebsiteDetailsService websiteDetailsService;

	@Autowired
	private EventDetailsService eventDetailsService;

	@GetMapping("eventDataScrapping")
	public ResponseEntity<ResponseDTO> eventDataScrapping() {
		return eventDetailsService.eventDataScrapping();
	}
	
	@GetMapping("eventDetails")
	public List<EventDetailsDTO> getEventDetails() {
		return eventDetailsService.getEventDetails();
	}

	@GetMapping("websiteDetails")
	public List<WebsiteDetailsDTO> getAllWebsiteDetails() {
		return websiteDetailsService.getAllWebsiteDetails();
	}

	@PostMapping("websiteDetails")
	public ResponseEntity<ResponseDTO> websiteDetails(@RequestBody WebsiteDetailsDTO websiteDetailsDTO) {
		return websiteDetailsService.saveWebsiteDetails(websiteDetailsDTO);
	}
}
