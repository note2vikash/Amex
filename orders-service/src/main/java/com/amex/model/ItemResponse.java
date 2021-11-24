/**
 * 
 */
package com.amex.model;

/**
 * @author EPAM
 *
 */
public class ItemResponse extends ItemRequest {
	
	private Integer offerId;
	private Double totalCost;
	private Double effectiveCost;

	public Double getEffectiveCost() {
		return effectiveCost;
	}

	public void setEffectiveCost(Double effectiveCost) {
		this.effectiveCost = effectiveCost;
	}

	public Integer getOfferId() {
		return offerId;
	}

	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	

}
