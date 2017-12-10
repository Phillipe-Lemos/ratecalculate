package com.bank.zopa.ratecalculate.controller.mapper;

import com.bank.zopa.ratecalculate.domain.Borrower;
import com.bank.zopa.ratecalculate.dto.BorrowerResponseDTO;
import com.bank.zopa.ratecalculate.dto.BorrowerResponseDTO.BorrowerResponseDTOBuilder;

public class BorrowerMapper {
	
	public static BorrowerResponseDTO makeBorrowerResponseDTO(Borrower borrower) {
		return new BorrowerResponseDTOBuilder().setAmountRequest(borrower.getTotalRequest())
				       .setMonthlyPayment(borrower.getTotalRepaiment() / borrower.getQtdParcelas())
				       .setRate(borrower.getAnualRate())
				       .setTotalPayment(borrower.getTotalRepaiment())
				       .createBorrowerResponseDTO();		
	}

}
