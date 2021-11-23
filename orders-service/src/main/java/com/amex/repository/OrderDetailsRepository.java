/**
 * 
 */
package com.amex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amex.entity.OrderDetail;

/**
 * @author EPAM
 *
 */
@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetail, Integer> {

}
