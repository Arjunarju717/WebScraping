package com.webscraping.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.webscraping.constants.RegistrationConstants;
import com.webscraping.dto.ResponseDTO;
import com.webscraping.dto.WebsiteDetailsDTO;

public class WebScrapingUtility {

	private static Logger logger = LogManager.getLogger(WebScrapingUtility.class);

	private static UrlValidator urlValidator = new UrlValidator();

	private WebScrapingUtility() {
	}

	public static boolean validateWebsiteDetailsDTO(WebsiteDetailsDTO websiteDetailsDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Validating website details DTO : %s", websiteDetailsDTO.toString()));
		}
		StringBuilder sb = new StringBuilder();
		boolean isValid = true;

		String name = websiteDetailsDTO.getName();
		if (isEmpty(name)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.WEBSITE_NAME_REQUIRED);
			isValid = false;
		}

		String url = websiteDetailsDTO.getUrl();
		if (isEmpty(url)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.WEBSITE_URL_REQUIRED);
			isValid = false;
		} else if (!isValidURL(url)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.WEBSITE_URL_IS_INVALID);
			isValid = false;
		}

		String elementType = websiteDetailsDTO.getElementType();
		if (isEmpty(elementType)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.ELEMENT_TYPE_IS_REQUIRED);
			isValid = false;
		}

		String elementId = websiteDetailsDTO.getElementId();
		String elementClass = websiteDetailsDTO.getElementClass();
		if (isEmpty(elementId) && isEmpty(elementClass)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.ELEMENT_ID_OR_CLASS_REQUIRED);
			isValid = false;
		} 

		int eventNameIndex = websiteDetailsDTO.getEventNameIndex();
		if (eventNameIndex < 0) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.EVENT_NAME_INDEX_INVALID);
			isValid = false;
		}

		int eventDateIndex = websiteDetailsDTO.getEventDateIndex();
		if (eventDateIndex < 0) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.EVENT_DATE_INDEX_INVALID);
			isValid = false;
		}

		int eventLoactionIndex = websiteDetailsDTO.getEventLocationIndex();
		if (eventLoactionIndex < 0) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.EVENT_LOCATION_INDEX_INVALID);
			isValid = false;
		}

		if (!isValid) {
			if (sb.length() != 0) {
				sb.append(".");
			}
			websiteDetailsDTO.setErrorMessage(sb.toString());
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Is WebsiteDetails valid : %s", isValid));
		}
		return isValid;
	}

	public static Long getUniqueID() {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside getUniqueID method");
		}
		DateFormat df = new SimpleDateFormat("yy");
		long year = Long.parseLong(df.format(Calendar.getInstance().getTime()) + "00000000");
		long randomNumber = (long) (Math.random() * 100000000L);
		return year + randomNumber;
	}

	public static ResponseEntity<ResponseDTO> fillResponseEntity(String message, HttpStatus status) {
		ResponseDTO responseDTO = new ResponseDTO(message);
		return new ResponseEntity<>(responseDTO, status);
	}

	public static boolean isEmpty(String stringValue) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside isEmpty method");
		}
		return stringValue == null || stringValue.trim().isEmpty();
	}

	public static boolean isValidURL(String url) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside isValidURL method");
		}
		return urlValidator.isValid(url);
	}

}
