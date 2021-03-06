/**
 * 
 */
package com.amex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amex.model.OrderRequest;
import com.amex.model.OrderResponse;
import com.amex.service.OrdersService;

/**
 * @author EPAM
 *
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {
	
	@Autowired
	private OrdersService service;

	@GetMapping(path="/{orderId}", consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<OrderResponse> getOrderById(@PathVariable(required = true) Integer orderId){
		return new ResponseEntity<>(service.getOrderById(orderId), HttpStatus.OK);
	}

	@GetMapping(consumes = {MediaType.ALL_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<OrderResponse>> getOrders(){
		return new ResponseEntity<>(service.getAllOrders(), HttpStatus.OK);
	}

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<OrderResponse> createOrder(@RequestBody(required = true) OrderRequest request){
		return new ResponseEntity<>(service.createOrder(request), HttpStatus.OK);
	}

}
