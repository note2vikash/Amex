/**
 * 
 */
package com.amex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amex.entity.Customer;

/**
 * @author EPAM
 *
 */
@Repository
public interface CustomersRepository extends JpaRepository<Customer, Integer> {

}
