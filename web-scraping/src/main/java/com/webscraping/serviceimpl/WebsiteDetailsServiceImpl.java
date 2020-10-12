package com.webscraping.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.webscraping.constants.RegistrationConstants;
import com.webscraping.dto.ResponseDTO;
import com.webscraping.dto.WebsiteDetailsDTO;
import com.webscraping.entity.WebsiteDetails;
import com.webscraping.repository.WebsiteDetailsRepository;
import com.webscraping.service.WebsiteDetailsService;
import com.webscraping.utility.WebScrapingUtility;

@Service
public class WebsiteDetailsServiceImpl implements WebsiteDetailsService {

	private Logger logger = LogManager.getLogger(WebsiteDetailsServiceImpl.class);

	@Autowired
	private WebsiteDetailsRepository websiteDetailsRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<ResponseDTO> saveWebsiteDetails(WebsiteDetailsDTO websiteDetailsDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Saving website details : %s", websiteDetailsDTO.toString()));
		}
		if (WebScrapingUtility.validateWebsiteDetailsDTO(websiteDetailsDTO)) {
			Date date = new Date();
			WebsiteDetails post = modelMapper.map(websiteDetailsDTO, WebsiteDetails.class);
			post.setWebsiteId(WebScrapingUtility.getUniqueID());
			post.setCreatedDate(date);
			post.setUpdatedDate(date);
			websiteDetailsRepository.save(post);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Webiste details saved successfully : %s", websiteDetailsDTO.toString()));
			}
			return WebScrapingUtility.fillResponseEntity(RegistrationConstants.WEBSITE_ADDED_SUCCESSFULLY,
					HttpStatus.CREATED);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Unable to save website details : %s", websiteDetailsDTO.getErrorMessage()));
			}
			return WebScrapingUtility.fillResponseEntity(websiteDetailsDTO.getErrorMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public List<WebsiteDetailsDTO> getAllWebsiteDetails() {
		if (logger.isDebugEnabled()) {
			logger.debug("Get all website details");
		}
		return websiteDetailsRepository.findAll().stream()
				.map(websiteDetails -> modelMapper.map(websiteDetails, WebsiteDetailsDTO.class))
				.collect(Collectors.toList());
	}
}
