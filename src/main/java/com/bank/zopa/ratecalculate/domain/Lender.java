package com.bank.zopa.ratecalculate.domain;

import com.bank.zopa.ratecalculate.util.FinancialUtil;

public class Lender  { 
	
	private String name;
	
	private Double rate;
	
	private Double available;
	
	private Double rateMonthly;

	public String getName() {
		return name;
	}

	public Double getRate() {
		return rate;
	}

	public Double getAvailable() {
		return available;
	}

	public Double getRateMonthly() {
		return rateMonthly;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public void setAvailable(Double available) {
		this.available = available;
	}

	public void setRateMonthly(Double rateMonthly) {
		this.rateMonthly = rateMonthly;
	}

	/**
	 * @param name
	 * @param rate
	 * @param available
	 */
	public Lender(String name, Double rate, Double available) {
		super();
		this.name = name;
		this.rate = rate;
		this.available = available;
		this.rateMonthly = FinancialUtil.anualRateToMonthlyRate(rate);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((available == null) ? 0 : available.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Lender)) {
			return false;
		}
		Lender other = (Lender) obj;
		if (available == null) {
			if (other.available != null) {
				return false;
			}
		} else if (!available.equals(other.available)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (rate == null) {
			if (other.rate != null) {
				return false;
			}
		} else if (!rate.equals(other.rate)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Lender [name=" + name + ", rate=" + rate + ", available=" + available + ", rateMonthly=" + rateMonthly
				+ "]";
	}

}
