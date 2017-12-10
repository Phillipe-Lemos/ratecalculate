package com.bank.zopa.ratecalculate.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bank.zopa.ratecalculate.domain.Borrower;
import com.bank.zopa.ratecalculate.domain.Lender;
import com.bank.zopa.ratecalculate.service.Impl.RateCalculeteServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RateCalculeteServiceImpl.class })
public class RateCalculateServiceTest {

	@Autowired
	private RateCalculeteService rateCalculeteService;
	
	private List<Lender> lenders;
	
	private static final String LINE_HEADER = "Lender,Rate,Available";
	
	@Before
	public void setUp() throws IOException{
		String filePath = ClassLoader.getSystemClassLoader().getResource("market.csv").getFile();
		lenders =  Files.lines(Paths.get(filePath))
		                .filter(line -> !line.equals(LINE_HEADER)) // remove the header line
					      .map(line -> line.split(","))
					      .map(params -> new Lender(params[0], 
					    		                    Double.valueOf(params[1]),
					    		                    Double.valueOf(params[2]))).collect(Collectors.toList());			
	}

	@Test
	public void loanValidValue(){
		Borrower borrower = rateCalculeteService.calculateLoanDetail(lenders, 1000D);
		assertThat(borrower, notNullValue());
		assertThat(borrower.getAnualRate(), equalTo(0.07004093302516612));
		assertThat(borrower.getQtdParcelas(), equalTo(36));
		assertThat(borrower.getTotalRepaiment(), equalTo(1225.1835980399999D));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void loanlessThan1000Pounds(){
		rateCalculeteService.calculateLoanDetail(lenders, 800D);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void loanNotMultipleFrom100Pounds(){
		rateCalculeteService.calculateLoanDetail(lenders, 1020D);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void loanExceds1500Pounds(){
		rateCalculeteService.calculateLoanDetail(lenders, 1600D);
	}

	@Test(expected = IllegalArgumentException.class)
	public void loanWithoutLenders(){
		rateCalculeteService.calculateLoanDetail(null, 1600D);
	}

	@Test(expected = IllegalArgumentException.class)
	public void loanWithoutRequestAmount(){
		rateCalculeteService.calculateLoanDetail(lenders, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void loanWithZeroRequestAmount(){
		rateCalculeteService.calculateLoanDetail(lenders, 0D);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void loanWithNegativeRequestAmount(){
		rateCalculeteService.calculateLoanDetail(lenders, -1D);
	}


	
}
