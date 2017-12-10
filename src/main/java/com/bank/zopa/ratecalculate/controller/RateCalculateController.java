package com.bank.zopa.ratecalculate.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bank.zopa.ratecalculate.controller.mapper.BorrowerMapper;
import com.bank.zopa.ratecalculate.domain.Borrower;
import com.bank.zopa.ratecalculate.domain.Lender;
import com.bank.zopa.ratecalculate.dto.BorrowerResponseDTO;
import com.bank.zopa.ratecalculate.service.RateCalculeteService;

@Component
public class RateCalculateController {
	
	private static Logger logger = LoggerFactory.getLogger(RateCalculateController.class);
	
	private static final String LINE_HEADER = "Lender,Rate,Available";
	
	@Autowired
	private RateCalculeteService rateCalculeteService;
	
	public BorrowerResponseDTO calculateRate(String filePath, Double totalRequested){
		
		logger.debug(">>> calculateRate " + filePath + " totalRequested " + totalRequested);
		
		if(filePath == null || filePath.isEmpty() || !Files.isRegularFile(Paths.get(filePath))){
			throw new IllegalArgumentException("You must suply a market file.");
		}
		
		if(totalRequested <= 0){
			throw new IllegalArgumentException("Request loan should not be a negative number. ");
		}
		
		try  {
			List<Lender> lenders = Files.lines(Paths.get(filePath))
					                  .filter(line -> !line.equals(LINE_HEADER)) // remove the header line
								      .map(line -> line.split(","))
								      .map(params -> new Lender(params[0], 
								    		                    Double.valueOf(params[1]),
								    		                    Double.valueOf(params[2]))).collect(Collectors.toList());
			
			Borrower borrower = rateCalculeteService.calculateLoanDetail(lenders, totalRequested); 
			
			return BorrowerMapper.makeBorrowerResponseDTO(borrower) ;
		} catch(IOException io){
			logger.error("Problemns reading file ", io);
			throw new IllegalArgumentException("Problems reading file " + filePath);
		} catch(Exception expcetion){
			logger.error("Problemns reading file ", expcetion);
			throw new IllegalArgumentException("Problems reading file " + filePath);
		}
	}

}
