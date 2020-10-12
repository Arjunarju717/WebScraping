package com.webscraping.dto;

import java.io.Serializable;

public class WebsiteDetailsDTO implements Serializable {

	private static final long serialVersionUID = 3469216630512799507L;

	private Long websiteId;
	private String name;
	private String url;
	private String elementType;
	private String elementId;
	private String elementClass;
	private int eventNameIndex;
	private int eventDateIndex;
	private int eventLocationIndex;
	private String errorMessage;

	public Long getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(Long websiteId) {
		this.websiteId = websiteId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getElementClass() {
		return elementClass;
	}

	public void setElementClass(String elementClass) {
		this.elementClass = elementClass;
	}

	public int getEventNameIndex() {
		return eventNameIndex;
	}

	public void setEventNameIndex(int eventNameIndex) {
		this.eventNameIndex = eventNameIndex;
	}

	public int getEventDateIndex() {
		return eventDateIndex;
	}

	public void setEventDateIndex(int eventDateIndex) {
		this.eventDateIndex = eventDateIndex;
	}

	public int getEventLocationIndex() {
		return eventLocationIndex;
	}

	public void setEventLocationIndex(int eventLocationIndex) {
		this.eventLocationIndex = eventLocationIndex;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elementClass == null) ? 0 : elementClass.hashCode());
		result = prime * result + ((elementId == null) ? 0 : elementId.hashCode());
		result = prime * result + ((elementType == null) ? 0 : elementType.hashCode());
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + eventDateIndex;
		result = prime * result + eventLocationIndex;
		result = prime * result + eventNameIndex;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((websiteId == null) ? 0 : websiteId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebsiteDetailsDTO other = (WebsiteDetailsDTO) obj;
		if (elementClass == null) {
			if (other.elementClass != null)
				return false;
		} else if (!elementClass.equals(other.elementClass))
			return false;
		if (elementId == null) {
			if (other.elementId != null)
				return false;
		} else if (!elementId.equals(other.elementId))
			return false;
		if (elementType == null) {
			if (other.elementType != null)
				return false;
		} else if (!elementType.equals(other.elementType))
			return false;
		if (errorMessage == null) {
			if (other.errorMessage != null)
				return false;
		} else if (!errorMessage.equals(other.errorMessage))
			return false;
		if (eventDateIndex != other.eventDateIndex)
			return false;
		if (eventLocationIndex != other.eventLocationIndex)
			return false;
		if (eventNameIndex != other.eventNameIndex)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (websiteId == null) {
			if (other.websiteId != null)
				return false;
		} else if (!websiteId.equals(other.websiteId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WebsiteDetailsDTO [websiteId=" + websiteId + ", name=" + name + ", url=" + url + ", elementType="
				+ elementType + ", elementId=" + elementId + ", elementClass=" + elementClass + ", eventNameIndex="
				+ eventNameIndex + ", eventDateIndex=" + eventDateIndex + ", eventLocationIndex=" + eventLocationIndex
				+ ", errorMessage=" + errorMessage + "]";
	}
}
