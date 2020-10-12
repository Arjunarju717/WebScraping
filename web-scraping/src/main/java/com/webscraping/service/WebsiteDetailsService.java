package com.webscraping.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.webscraping.dto.ResponseDTO;
import com.webscraping.dto.WebsiteDetailsDTO;

public interface WebsiteDetailsService {
	
	public ResponseEntity<ResponseDTO> saveWebsiteDetails(WebsiteDetailsDTO websiteDetailsDTO);
	
	public List<WebsiteDetailsDTO> getAllWebsiteDetails();

}
