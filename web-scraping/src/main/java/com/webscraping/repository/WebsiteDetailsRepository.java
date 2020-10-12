package com.webscraping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webscraping.entity.WebsiteDetails;

@Repository
public interface WebsiteDetailsRepository extends JpaRepository<WebsiteDetails, Long>{

}
