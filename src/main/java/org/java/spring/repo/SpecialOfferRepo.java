package org.java.spring.repo;

import java.util.List;

import org.java.spring.pojo.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialOfferRepo extends JpaRepository<SpecialOffer, Integer> {
	public List<SpecialOffer> findByDeletedFalse();
	
	public List<SpecialOffer> findByDeletedTrue();
}
