/**
 * 
 */
package com.amex.model;

import java.util.List;

/**
 * @author EPAM
 *
 */
public class OrderRequest {
	
	private Integer customerId;
	private List<ItemRequest> items;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public List<ItemRequest> getItems() {
		return items;
	}
	public void setItems(List<ItemRequest> items) {
		this.items = items;
	}
	
	

}
