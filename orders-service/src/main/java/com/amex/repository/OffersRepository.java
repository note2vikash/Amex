/**
 * 
 */
package com.amex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amex.entity.Offer;

/**
 * @author EPAM
 *
 */
@Repository
public interface OffersRepository extends JpaRepository<Offer, Integer> {

}
