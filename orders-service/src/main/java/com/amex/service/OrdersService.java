/**
 * 
 */
package com.amex.service;

import java.util.List;

import com.amex.model.OrderRequest;
import com.amex.model.OrderResponse;

/**
 * @author EPAM
 *
 */
public interface OrdersService {
	
	OrderResponse getOrderById(Integer orderId);
	
	List<OrderResponse> getAllOrders();
	
	OrderResponse createOrder(OrderRequest request);

}
