/**
 * 
 */
package com.amex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amex.entity.Item;

/**
 * @author EPAM
 *
 */
@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {

}
