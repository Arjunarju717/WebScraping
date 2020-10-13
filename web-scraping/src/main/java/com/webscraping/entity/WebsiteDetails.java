package com.webscraping.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "websitedetails")
public class WebsiteDetails implements Serializable {

	private static final long serialVersionUID = 5444376498797279232L;

	@Id
	@Column(name = "websiteid")
	private Long websiteId;

	@Column(name = "name")
	private String name;

	@Column(name = "url")
	private String url;

	@Column(name = "elementype")
	private String elementType;

	@Column(name = "elementid")
	private String elementId;

	@Column(name = "elementclass")
	private String elementClass;

	@Column(name = "eventnameindex")
	private int eventNameIndex;

	@Column(name = "eventdateindex")
	private int eventDateIndex;

	@Column(name = "eventlocationindex")
	private int eventLocationIndex;

	@Column(name = "createddate")
	private Date createdDate;

	@Column(name = "updateddate")
	private Date updatedDate;

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((elementClass == null) ? 0 : elementClass.hashCode());
		result = prime * result + ((elementId == null) ? 0 : elementId.hashCode());
		result = prime * result + ((elementType == null) ? 0 : elementType.hashCode());
		result = prime * result + eventDateIndex;
		result = prime * result + eventLocationIndex;
		result = prime * result + eventNameIndex;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
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
		WebsiteDetails other = (WebsiteDetails) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
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
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
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
		return "WebsiteDetails [websiteId=" + websiteId + ", name=" + name + ", url=" + url + ", elementType="
				+ elementType + ", elementId=" + elementId + ", elementClass=" + elementClass + ", eventNameIndex="
				+ eventNameIndex + ", eventDateIndex=" + eventDateIndex + ", eventLocationIndex=" + eventLocationIndex
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}

}
