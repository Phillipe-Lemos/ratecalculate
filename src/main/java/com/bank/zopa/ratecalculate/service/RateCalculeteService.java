package com.bank.zopa.ratecalculate.service;

import java.util.List;

import com.bank.zopa.ratecalculate.domain.Borrower;
import com.bank.zopa.ratecalculate.domain.Lender;

public interface RateCalculeteService {

	Borrower calculateLoanDetail(List<Lender> lenders, Double totalRequested);
	
}
