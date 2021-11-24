/**
 * 
 */
package com.amex.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.amex.entity.Item;
import com.amex.entity.Offer;
import com.amex.entity.Order;
import com.amex.entity.OrderDetail;
import com.amex.model.ItemRequest;
import com.amex.model.ItemResponse;
import com.amex.model.OrderRequest;
import com.amex.model.OrderResponse;
import com.amex.repository.ItemsRepository;
import com.amex.repository.OrdersRepository;

/**
 * @author EPAM
 *
 */
@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private ItemsRepository itemsRepository;

	@Autowired
	private OrdersRepository ordersRepository;
	
	@Override
	public OrderResponse getOrderById(Integer orderId) {
		Order order = ordersRepository.getById(orderId);
		return generateResponse(order);
	}

	@Override
	public List<OrderResponse> getAllOrders() {
		List<Order> orders = ordersRepository.findAll(Sort.by("id"));
		final List<OrderResponse> orderResponses = new ArrayList<>();
		orders.forEach(order->orderResponses.add(generateResponse(order)));
		return orderResponses;

	}

	@Override
	@Transactional()
	public OrderResponse createOrder(OrderRequest request) {
		// Fetch all Items
		List<Item> allItems = itemsRepository.findAll(Sort.by("id"));
		// Create Order
		Date createdOn = new Date();
		Order order = new Order();
		order.setCustomerId(request.getCustomerId());
		order.setCreatedOn(createdOn);
		// Create OrderDetails
		List<ItemRequest> itemRequests = request.getItems();
		List<OrderDetail> orderDetails = new ArrayList<>();
		List<ItemResponse> itemsOdered = new ArrayList<>();
		Double totalCost = 0.0;
		for(ItemRequest itemRequest : itemRequests ) {
			int itemId = itemRequest.getItemId();
			int quantity = itemRequest.getQuantity();
			Item item = allItems.get(itemRequest.getItemId()-1); // itemId starts from 1 and index of allItems starts from 0
			Double actualPrice = item.getPrice();
			List<Offer> offers = item.getOffers();
			Integer offerId = null;
			if (!CollectionUtils.isEmpty(offers)) {
				offerId = offers.get(0).getId();
			}
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setItemId(itemId);
			orderDetail.setQuantity(quantity);
			orderDetail.setTotalPrice(quantity*actualPrice);
			orderDetail.setOfferId(offerId);
			orderDetail.setOrder(order);
			// Calculate discounted price
			Double discountedPrice = getDiscountedPrice(item, offerId, quantity);
			orderDetail.setDiscountedPrice(discountedPrice);
			// update total cost
			totalCost = totalCost + discountedPrice;
			orderDetails.add(orderDetail);
			ItemResponse itemResponse = new ItemResponse();
			itemResponse.setItemId(itemId);
			itemResponse.setQuantity(quantity);
			itemResponse.setOfferId(offerId);
			itemsOdered.add(itemResponse);
		}
		order.setOrderDetails(orderDetails);
		order = ordersRepository.save(order);
		OrderResponse response = new OrderResponse();
		response.setOederId(order.getId());
		response.setCreatedOn(createdOn);
		response.setCustomerId(request.getCustomerId());
		response.setItemsOrdered(itemsOdered);
		response.setTotalCost(totalCost);
		return response;
	}

	/**
	 * 
	 * @param order
	 * @return
	 */
	private OrderResponse generateResponse(Order order) {
		OrderResponse response = new OrderResponse();
		response.setOederId(order.getId());
		response.setCreatedOn(order.getCreatedOn());
		response.setCustomerId(order.getCustomerId());
		List<ItemResponse> itemsOdered = new ArrayList<>();
		Double totalCost = 0.0;
		List<OrderDetail> orderDetails = order.getOrderDetails();
		for(OrderDetail details : orderDetails) {
			ItemResponse itemResponse = new ItemResponse();
			itemResponse.setItemId(details.getItemId());
			itemResponse.setQuantity(details.getQuantity());
			itemResponse.setOfferId(details.getOfferId());
			totalCost = totalCost + details.getDiscountedPrice();
			itemsOdered.add(itemResponse);
		}
		response.setItemsOrdered(itemsOdered);
		response.setTotalCost(totalCost);
		return response;
	}

	/**
	 * 
	 * @param item
	 * @param offerId
	 * @param quantity
	 * @return
	 */
	private Double getDiscountedPrice(Item item, Integer offerId, Integer quantity) {
		Double price = 0.0;
		if (item.getId() == 1 && offerId == 1) {
			price = item.getPrice()*Math.ceil(quantity/2.0);
		}else if(item.getId() == 2 && offerId == 2) {
			if (quantity < 3) {
				price = item.getPrice()*quantity;
			}else {
				if (quantity % 3 == 0) {
					price = (item.getPrice()*2)*quantity/3;
				}else {
					while(quantity > 1) {
						int extra = quantity%3;
						quantity = quantity/3;
						price = price + ((item.getPrice()*2)*quantity + item.getPrice()*extra);
					}
				}
			}
		}else {
			// Do nothing
		}
		return price;
	}

}
