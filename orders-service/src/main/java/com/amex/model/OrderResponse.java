/**
 * 
 */
package com.amex.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author EPAM
 *
 */
public class OrderResponse extends OrderRequest {
	
	
	private Integer oederId;
	private Date createdOn;
	private List<ItemResponse> itemsOrdered;
	private Double totalCost;
	@JsonIgnore
	private List<ItemRequest> items;
	
	public Integer getOederId() {
		return oederId;
	}
	public void setOederId(Integer oederId) {
		this.oederId = oederId;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public List<ItemResponse> getItemsOrdered() {
		return itemsOrdered;
	}
	public void setItemsOrdered(List<ItemResponse> itemsOrdered) {
		this.itemsOrdered = itemsOrdered;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
}
