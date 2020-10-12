package com.webscraping.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.webscraping.constants.RegistrationConstants;
import com.webscraping.constants.WebScrappingConstants;
import com.webscraping.dto.EventDetailsDTO;
import com.webscraping.dto.ResponseDTO;
import com.webscraping.entity.EventDetails;
import com.webscraping.entity.WebsiteDetails;
import com.webscraping.repository.EventDetailsRepository;
import com.webscraping.repository.WebsiteDetailsRepository;
import com.webscraping.service.EventDetailsService;
import com.webscraping.service.WebClientService;
import com.webscraping.utility.WebScrapingUtility;
import com.webscraping.wsenum.Entity;

@Service
public class EventDetailsServiceImpl implements EventDetailsService {

	private Logger logger = LogManager.getLogger(EventDetailsServiceImpl.class);

	@Autowired
	private WebsiteDetailsRepository websiteDetailsRepository;

	@Autowired
	private EventDetailsRepository eventDetailsRepository;

	@Autowired
	private WebClientService webClientService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<ResponseDTO> eventDataScrapping() {
		if (logger.isDebugEnabled()) {
			logger.debug("Scraping event data");
		}
		List<EventDetails> eventDetailsList = new ArrayList<>();
		try {
			List<WebsiteDetails> websiteDetailsList = websiteDetailsRepository.findAll();
			if (!websiteDetailsList.isEmpty()) {
				Set<Callable<List<EventDetails>>> callableSet = createCallableSet(websiteDetailsList);
				ExecutorService executorService = Executors.newFixedThreadPool(websiteDetailsList.size());
				List<Future<List<EventDetails>>> futures = executorService.invokeAll(callableSet);
				for (Future<List<EventDetails>> future : futures) {
					eventDetailsList.addAll(future.get());
				}
				eventDetailsRepository.saveAll(eventDetailsList);
				return WebScrapingUtility.fillResponseEntity(RegistrationConstants.SCRAPE_EVENT_SUCCESSFULLY,
						HttpStatus.OK);
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug(WebScrappingConstants.NO_WEBSITE_FOUND_EXCEPTION);
				}
				return WebScrapingUtility.fillResponseEntity(WebScrappingConstants.NO_WEBSITE_FOUND_EXCEPTION,
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Exception occured while fetching all event data : %s", e));
			}
			return WebScrapingUtility.fillResponseEntity(WebScrappingConstants.WEBSCRAPING_EXCEPTION,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Set<Callable<List<EventDetails>>> createCallableSet(List<WebsiteDetails> websiteDetailsList) {
		if (logger.isDebugEnabled()) {
			logger.debug("Creating callables for all the websites");
		}
		Set<Callable<List<EventDetails>>> callableSet = new HashSet<>();
		for (WebsiteDetails websiteDetails : websiteDetailsList) {
			if (websiteDetails.getElementType().equals(Entity.Table.name())) {
				callableSet.add(webClientService.getTableData(websiteDetails));
			} else if (websiteDetails.getElementType().equals(Entity.Anchor.name())) {
				callableSet.add(webClientService.getAnchorData(websiteDetails));
			}
		}
		return callableSet;
	}
	
	@Override
	public List<EventDetailsDTO> getEventDetails() {
		if (logger.isDebugEnabled()) {
			logger.debug("Get event details");
		}
		return eventDetailsRepository.getAllEventDetails().stream().map(eventDetails -> modelMapper.map(eventDetails, EventDetailsDTO.class))
				.collect(Collectors.toList());
	}
}
