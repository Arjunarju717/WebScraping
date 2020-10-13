package com.webscraping.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.webscraping.entity.EventDetails;
import com.webscraping.entity.WebsiteDetails;
import com.webscraping.service.WebClientService;

@Service
public class WebClientServiceImpl implements WebClientService {

	private Logger logger = LogManager.getLogger(WebClientServiceImpl.class);

	private final WebClient client;

	public WebClientServiceImpl() {
		client = new WebClient(BrowserVersion.CHROME);
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		client.getOptions().setThrowExceptionOnFailingStatusCode(false);
		client.getOptions().setThrowExceptionOnScriptError(false);
	}

	@Override
	public Callable<List<EventDetails>> getTableData(WebsiteDetails websiteDetails) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Get table data for website : %s",
					websiteDetails.getName() != null ? websiteDetails.getName() : null));
		}
		return new Callable<List<EventDetails>>() {
			@Override
			public List<EventDetails> call() throws Exception {
				List<EventDetails> eventDetailsList = new ArrayList<>();
				Date date = new Date();
				try {
					HtmlPage page = getHtmlPage(websiteDetails.getUrl());
					String idOrClass = websiteDetails.getElementId() != null ? websiteDetails.getElementId()
							: websiteDetails.getElementClass();
					final HtmlTable table = page.getHtmlElementById(idOrClass);
					int count = 0;
					for (final HtmlTableRow row : table.getRows()) {
						if (count != 0) {
							EventDetails eventDetails = new EventDetails();
							eventDetails.setWebsiteName(websiteDetails.getName());
							eventDetails.setEventName(row.getCell(websiteDetails.getEventNameIndex()).asText());
							eventDetails.setEventDate(row.getCell(websiteDetails.getEventDateIndex()).asText());
							eventDetails.setEventLocation(row.getCell(websiteDetails.getEventLocationIndex()).asText());
							eventDetails.setCreatedDate(date);
							eventDetailsList.add(eventDetails);
						}
						count++;
					}
				} catch (Exception e) {
					if (logger.isDebugEnabled()) {
						logger.debug(String.format("Exception occured while getting table data : %s", e));
					}
				}
				return eventDetailsList;
			}
		};
	}

	@SuppressWarnings("unchecked")
	@Override
	public Callable<List<EventDetails>> getAnchorData(WebsiteDetails websiteDetails) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Get anchor data for website : %s",
					websiteDetails.getName() != null ? websiteDetails.getName() : null));
		}
		return new Callable<List<EventDetails>>() {
			@Override
			public List<EventDetails> call() throws Exception {
				List<EventDetails> eventDetailsList = new ArrayList<>();
				Date date = new Date();
				try {
					HtmlPage page = getHtmlPage(websiteDetails.getUrl());
					String idOrClass = websiteDetails.getElementId() != null ? websiteDetails.getElementId()
							: websiteDetails.getElementClass();
					List<HtmlAnchor> divList = (List<HtmlAnchor>) page.getByXPath(idOrClass);
					for (final HtmlAnchor div : divList) {
						DomNodeList<DomNode> childnodelist = div.getChildNodes();
						int count = 0;
						EventDetails eventDetails = new EventDetails();
						eventDetails.setWebsiteName(websiteDetails.getName());
						eventDetails.setCreatedDate(date);
						for (final DomNode childnode : childnodelist) {
							if (count == websiteDetails.getEventNameIndex()) {
								eventDetails.setEventName(childnode.getTextContent());
							} else if (count == websiteDetails.getEventDateIndex()) {
								eventDetails.setEventDate(childnode.getTextContent());
							} else if (count == websiteDetails.getEventLocationIndex()) {
								eventDetails.setEventLocation(childnode.getTextContent());
							}
							count++;
						}
						eventDetailsList.add(eventDetails);
					}
				} catch (Exception e) {
					if (logger.isDebugEnabled()) {
						logger.debug(String.format("Exception occured while getting anchor data : %s", e));
					}
				}
				return eventDetailsList;
			}
		};
	}

	private HtmlPage getHtmlPage(final String url) throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Get html page for url : %s", url));
		}
		return client.getPage(url);
	}
}
