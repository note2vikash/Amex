/**
 * 
 */
package com.amex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amex.entity.Order;

/**
 * @author EPAM
 *
 */
@Repository
public interface OrdersRepository extends JpaRepository<Order, Integer> {

}
