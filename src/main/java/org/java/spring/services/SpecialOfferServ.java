package org.java.spring.services;

import java.util.List;
import java.util.Optional;

import org.java.spring.pojo.SpecialOffer;
import org.java.spring.repo.SpecialOfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferServ {
	@Autowired
	private SpecialOfferRepo repo;
	
	/**
	 * 
	 * Return all SpecialOffer elements in the database
	 * @return specialOffers
	 */
	public List<SpecialOffer> findAll() {
		return repo.findAll();
	}

	/**
	 * 
	 * Return all SpecialOffer elements in the database with the boolean "deleted" set to false
	 * @return specialOffers
	 */
	public List<SpecialOffer> findAllAvailableSpecialOffers() {
		return repo.findByDeletedFalse();
	}
	
	/**
	 * 
	 * Return all SpecialOffer elements in the database with the boolean "deleted" set to true
	 * @return specialOffers
	 */
	public List<SpecialOffer> findAllTrashedSpecialOffers() {
		return repo.findByDeletedTrue();
	}
	
	/**
	 * 
	 * Return a SpecialOffer element with an identical id to the given ones
	 * @return specialOfferOpt
	 */
	public Optional<SpecialOffer> findById(int id) {
		return repo.findById(id);
	}
	
	/**
	 * 
	 * Save an element in the SpecialOffer table of the database
	 */
	public void save(SpecialOffer specialOffer) {
		repo.save(specialOffer);
	}
	
	/**
	 * 
	 * Delete an element from the SpecialOffer table of the database
	 */
	public void delete(SpecialOffer specialOffer) {
		repo.delete(specialOffer);
	}
	
	/**
	 * 
	 * Delete all elements from the SpecialOffer table of the database
	 */
	public void deleteAll(List<SpecialOffer> specialOffers) {
		repo.deleteAll(specialOffers);
	}
}
