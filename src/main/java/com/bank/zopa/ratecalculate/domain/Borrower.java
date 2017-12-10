package com.bank.zopa.ratecalculate.domain;

import com.bank.zopa.ratecalculate.util.FinancialUtil;

public class Borrower {
	
	private Double totalRequest;
	
	private Double totalRepaiment;
	
	private Double anualRate;
	
	private Integer qtdParcelas;
	
	public Double getTotalRequest() {
		return totalRequest;
	}

	public Double getAnualRate() {
		return anualRate;
	}

	public void setTotalRequest(Double totalRequest) {
		this.totalRequest = totalRequest;
	}


	public void setAnualRate(Double anualRate) {
		this.anualRate = anualRate;
	}

	/**
	 * @param totalRequest
	 */
	public Borrower(Double totalRequest, Double totalRepaiment) {
		super();
		this.totalRequest = totalRequest;
		this.totalRepaiment = totalRepaiment;
		this.qtdParcelas = 36;
		this.anualRate = FinancialUtil.monthlyRateToAnualRate(FinancialUtil.finalRate(totalRepaiment, totalRequest, qtdParcelas));
		
	}

	public Double getTotalRepaiment() {
		return totalRepaiment;
	}


	public void setTotalRepaiment(Double totalRepaiment) {
		this.totalRepaiment = totalRepaiment;
	}


	public Integer getQtdParcelas() {
		return qtdParcelas;
	}

}
