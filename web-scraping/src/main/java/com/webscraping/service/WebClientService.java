package com.webscraping.service;

import java.util.List;
import java.util.concurrent.Callable;

import com.webscraping.entity.EventDetails;
import com.webscraping.entity.WebsiteDetails;

public interface WebClientService {
	
	public Callable<List<EventDetails>> getTableData(WebsiteDetails websiteDetails);
	
	public Callable<List<EventDetails>> getAnchorData(WebsiteDetails websiteDetails);

}
