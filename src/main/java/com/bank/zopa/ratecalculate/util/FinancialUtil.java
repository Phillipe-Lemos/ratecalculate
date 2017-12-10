package com.bank.zopa.ratecalculate.util;

public class FinancialUtil {
	
	public static double anualRateToMonthlyRate(final double anualRate){
		return Math.pow((1D + anualRate), 1D/12D) -1D;
	}
	
	public static double monthlyRateToAnualRate(final double monthlyRate){
		return Math.pow((1D + monthlyRate), 12D) -1D;
	}
	
	public static double totalPayments(final double monthlyRate, final double value, final int periods){
		return value * Math.pow((1 + monthlyRate), periods); 
	}
	
	public static double finalRate(final double totalRepaiments, final double totalRequested, final int periods){
		if(totalRequested== 0D || periods == 0){
			return 0D;
		}
		return Math.pow((totalRepaiments / totalRequested), 1D/Integer.valueOf(periods).doubleValue()) - 1D;
	}
}