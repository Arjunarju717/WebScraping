package com.webscraping.customrepositoryimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.webscraping.customrepository.CustomEventDetailsRepository;
import com.webscraping.entity.EventDetails;

public class CustomEventDetailsRepositoryImpl implements CustomEventDetailsRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<EventDetails> getAllEventDetails() {
		
		String sql= "select *\r\n" + 
				"from eventdetails as ed\r\n" + 
				"inner join (\r\n" + 
				"    select etd.websitename, max(etd.createddate) as MaxDate\r\n" + 
				"    from eventdetails as etd\r\n" + 
				"    group by etd.websitename\r\n" + 
				") as edtemp on ed.websitename = edtemp.websitename and ed.createddate = edtemp.MaxDate";
		Query query = entityManager.createNativeQuery(sql, EventDetails.class);
		return query.getResultList();
	}

}
