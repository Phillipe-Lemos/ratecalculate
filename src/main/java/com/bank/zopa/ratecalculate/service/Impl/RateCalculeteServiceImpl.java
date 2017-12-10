package com.bank.zopa.ratecalculate.service.Impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bank.zopa.ratecalculate.domain.Borrower;
import com.bank.zopa.ratecalculate.domain.Lender;
import com.bank.zopa.ratecalculate.service.RateCalculeteService;
import com.bank.zopa.ratecalculate.util.FinancialUtil;

/**
 * Service responsable for validate the input and calculate total repayment.
 *   
 * @author phillipe
 *
 */
@Service
public class RateCalculeteServiceImpl implements RateCalculeteService {

	private static Logger logger = LoggerFactory.getLogger(RateCalculeteServiceImpl.class);
	
	private final static double MAX_LOAN_AMOUNT = 1500D;
	
	private final static double MIN_LOAN_AMOUNT = 1000D;
	
	/**
	 * Validate parameters.
	 * 
	 * @param  lenders          List of leaders that form market. They come from a cvs file. 
	 * @param  totalRequested   Total loan amount.
	 * @return A null value returned indicate that parameters are valid.                 
	 */
	private void validate(List<Lender> lenders, Double totalRequested){
		if(totalRequested == null){
			throw new IllegalArgumentException("You must inform a loan amount.");
		}
		
		if(totalRequested > MAX_LOAN_AMOUNT){
			throw new IllegalArgumentException("The total requested exceeded maximum value to loan.");
		} 
		
		if(totalRequested < MIN_LOAN_AMOUNT){
			throw new IllegalArgumentException("The total requested is under minimum value to loan.");
		}
		
		if(totalRequested % 100 != 0){
			throw new IllegalArgumentException("The loan requested should be multiple from 100 Pounds");
		}
		
		if(lenders == null || lenders.isEmpty()){
			throw new IllegalArgumentException("The lenders file is empty.");
		}
		
		double totalAvailable = lenders.stream().collect(Collectors.summingDouble(lender -> lender.getAvailable()));
		
		if(totalAvailable < totalRequested){
			throw new IllegalArgumentException("Does not have sufficient offers from lenders to satisfy the loan.");
		}
	}
	
	/**
	 * Calculate total repayment and final rate from a list of lenders and a total amount requested. Incapsulate this information
	 * in a Borrower object that calculate the anual final rate. To optimize the process the list is sorted in acescending order from 
	 * rates and descending order from available. 
	 * 
	 * @param lenders         List of lenders. 
	 * @param totalRequested  Total amount requested.
	 * @throws IllegalArgumentException if a parameter is not valid.
	 * 
	 */
	@Override
	public Borrower calculateLoanDetail(List<Lender> lenders, Double totalRequested) {
		validate(lenders, totalRequested); 
		// from less rate to biggest rate
		Comparator<Lender> comparator = (l1,l2) -> {
			if(l1.getRate().doubleValue() > l2.getRate().doubleValue()){
				return 1;
			} else if(l1.getRate().equals(l2.getRate())){
			  if(l1.getAvailable().doubleValue() > l2.getAvailable().doubleValue()){
				  return -1;
			  } else if(l1.getAvailable().equals(l2.getAvailable())){
				  return 0;
			  } else {
				  return 1;
			  }
			} else {
				return -1;
			}
		};
		
		Collections.sort(lenders, comparator);
		logger.debug(lenders.toString());
		Double totRequested = totalRequested;
		List<Lender> lenderChoose = new ArrayList<>();
		for(Lender lender : lenders){
			if(totRequested > 0D){
				totRequested = totRequested - lender.getAvailable();
				lenderChoose.add(lender);
			} else {
				break;
			}
		}
		
		Double totalRepaiment = lenderChoose
				                   .stream()
		                             .collect(Collectors
		    		                  .summingDouble(lender -> FinancialUtil.totalPayments(lender.getRateMonthly(), lender.getAvailable(), 36)));
		
		return new Borrower(totalRequested,totalRepaiment);
	} 

}
