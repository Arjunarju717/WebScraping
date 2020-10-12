package com.webscraping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webscraping.customrepository.CustomEventDetailsRepository;
import com.webscraping.entity.EventDetails;

@Repository
public interface EventDetailsRepository extends JpaRepository<EventDetails, Long> , CustomEventDetailsRepository {

}
