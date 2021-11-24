/**
 * 
 */
package com.amex.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import com.amex.entity.Item;
import com.amex.entity.Offer;
import com.amex.entity.Order;
import com.amex.entity.OrderDetail;
import com.amex.model.ItemRequest;
import com.amex.model.OrderRequest;
import com.amex.model.OrderResponse;
import com.amex.repository.ItemsRepository;
import com.amex.repository.OrdersRepository;


/**
 * @author EPAM
 *
 */
public class OrdersServiceImplTest {
	
	@Mock
	private ItemsRepository itemsRepository;

	@Mock
	private OrdersRepository ordersRepository;
	
	@InjectMocks
	private OrdersServiceImpl service ;
	
	private Item item;
	private Offer offer;
	private Order order;
	private OrderDetail orderDetails;
	private List<Order> orders;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		// Set Item
		item = new Item();
		item.setId(1);
		item.setName("Apple");
		item.setPrice(60.0);
		
		offer = new Offer();
		offer.setId(1);
		offer.setName("buyOneGetOneFree");
		
		item.setOffers(Arrays.asList(offer));
		
		order = new Order();
		order.setId(1);
		order.setCustomerId(1);
		
		orderDetails = new OrderDetail();
		orderDetails.setId(1);
		orderDetails.setItemId(1);
		orderDetails.setQuantity(3);
		orderDetails.setOfferId(1);
		orderDetails.setDiscountedPrice(120.0);
		orderDetails.setOrder(order);
		
		order.setOrderDetails(Arrays.asList(orderDetails));
		
		orders = new ArrayList<>();
		orders.add(order);
		
		when(itemsRepository.getById(1)).thenReturn(item);
		when(itemsRepository.findAll(Sort.by("id"))).thenReturn(Arrays.asList(item));
	}

	/**
	 * Test method for {@link com.amex.service.OrdersServiceImpl#getOrderById(java.lang.Integer)}.
	 */
	@Test
	public void testGetOrderById() {
		when(ordersRepository.getById(1)).thenReturn(order);
		OrderResponse orderById = service.getOrderById(1);
		assertTrue(orderById.getOederId()==1);
	}

	/**
	 * Test method for {@link com.amex.service.OrdersServiceImpl#getAllOrders()}.
	 */
	@Test
	public void testGetAllOrders() {
		when(ordersRepository.findAll(Sort.by("id"))).thenReturn(orders);
		List<OrderResponse> allOrders = service.getAllOrders();
		assertNotEquals(allOrders, orders);
	}

	/**
	 * Test method for {@link com.amex.service.OrdersServiceImpl#createOrder(com.amex.model.OrderRequest)}.
	 */
	@Test
	public void testCreateOrder() {
		OrderRequest req = new OrderRequest();
		req.setCustomerId(1);
		ItemRequest iReq = new ItemRequest();
		iReq.setItemId(1);
		iReq.setQuantity(3);
		req.setItems(Arrays.asList(iReq));
		when(ordersRepository.findAll(Sort.by("id"))).thenReturn(orders);
		when( ordersRepository.save(Matchers.any(Order.class))).thenReturn(order);
		OrderResponse createOrder = service.createOrder(req);
		assertEquals(Double.valueOf(120.0), createOrder.getTotalCost());
	}

}
