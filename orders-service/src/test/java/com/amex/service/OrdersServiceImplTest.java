/**
 * 
 */
package com.amex.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.amex.entity.Order;
import com.amex.model.OrderResponse;
import com.amex.repository.ItemsRepository;
import com.amex.repository.OrdersRepository;

/**
 * @author EPAM
 *
 */
@RunWith(MockitoJUnitRunner.class)
class OrdersServiceImplTest {
	
	@Mock
	private ItemsRepository itemsRepository;

	@Mock
	private OrdersRepository ordersRepository;
	
	@InjectMocks
	private OrdersService service ;
	
	@SuppressWarnings("deprecation")
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test method for {@link com.amex.service.OrdersServiceImpl#getOrderById(java.lang.Integer)}.
	 */
	@Test
	void testGetOrderById() {
		Order order = new Order();
		order.setCustomerId(123);
		Optional<Order> op = Optional.of(order);
		when(ordersRepository.findById(123)).thenReturn(op);
		OrderResponse orderById = service.getOrderById(123);
		assertTrue(orderById.getOederId()==123);
	}

	/**
	 * Test method for {@link com.amex.service.OrdersServiceImpl#getAllOrders()}.
	 */
	@Test
	void testGetAllOrders() {
	}

	/**
	 * Test method for {@link com.amex.service.OrdersServiceImpl#createOrder(com.amex.model.OrderRequest)}.
	 */
	@Test
	void testCreateOrder() {
	}

}
