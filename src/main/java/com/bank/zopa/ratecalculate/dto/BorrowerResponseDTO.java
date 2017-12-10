package com.bank.zopa.ratecalculate.dto;

import java.text.NumberFormat;
import java.util.Locale;

public class BorrowerResponseDTO {
	
	private Double amountRequest;
	
	private Double rate;
	
	private Double monthlyPayment;
	
	private Double totalPayment;

	public Double getAmountRequest() {
		return amountRequest;
	}

	public Double getRate() {
		return rate;
	}

	public Double getMonthlyPayment() {
		return monthlyPayment;
	}

	public Double getTotalPayment() {
		return totalPayment;
	} 
	
	/**
	 * @param amountRequest
	 * @param rate
	 * @param monthlyPayment
	 * @param totalPayment
	 */
	private BorrowerResponseDTO(Double amountRequest, 
	                            Double rate, 
			                    Double monthlyPayment,
			                    Double totalPayment) {
		super();
		this.amountRequest = amountRequest;
		this.rate = rate;
		this.monthlyPayment = monthlyPayment;
		this.totalPayment = totalPayment;
	}
	
	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getInstance(Locale.UK);
		nf.setMaximumFractionDigits(1);
		nf.setMinimumFractionDigits(1);
		NumberFormat nf1 = NumberFormat.getCurrencyInstance(Locale.UK);
		nf1.setMaximumFractionDigits(2);
		return "Request amount    : " + nf1.format(amountRequest) + "\n" + 
               "Rate              : " + nf.format(rate * 100) + "% \n" +
			   "Monthly repayment : " + nf1.format(monthlyPayment) + "\n" +
               "Total repayment   : " + nf1.format(totalPayment);
	}

	
	public static class BorrowerResponseDTOBuilder {
		private Double amountRequest;
		
		private Double rate;
		
		private Double monthlyPayment;
		
		private Double totalPayment;

		public BorrowerResponseDTOBuilder setAmountRequest(Double amountRequest) {
			this.amountRequest = amountRequest;
			return this;
		}

		public BorrowerResponseDTOBuilder setRate(Double rate) {
			this.rate = rate;
			return this;
		}

		public BorrowerResponseDTOBuilder setMonthlyPayment(Double monthlyPayment) {
			this.monthlyPayment = monthlyPayment;
			return this;
		}

		public BorrowerResponseDTOBuilder setTotalPayment(Double totalPayment) {
			this.totalPayment = totalPayment;
			return this;
		}
		
		public BorrowerResponseDTOBuilder newBuilder(){
			return new BorrowerResponseDTOBuilder();
		}
		
		public BorrowerResponseDTO createBorrowerResponseDTO(){
			return new BorrowerResponseDTO(amountRequest,rate,monthlyPayment,totalPayment);
		}
	}
}
