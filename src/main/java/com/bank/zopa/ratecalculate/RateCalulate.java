package com.bank.zopa.ratecalculate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.bank.zopa.ratecalculate.controller.RateCalculateController;

public class RateCalulate {
	
	private static final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.bank.zopa.ratecalculate");
	
	private static Logger logger = LoggerFactory.getLogger(RateCalulate.class);
	
	public static void main(String...params){
		if(params.length ==  2){
			RateCalulate recalculate = new RateCalulate();
			System.out.println();
			System.out.println(recalculate.run(params[0], params[1]));
		} else {
			System.out.println("Missing parameters : You must supply the file market.csv and total loan.");
			System.exit(-1);
		}
	}
	
	private String run(String pathString, String value){
		String msg = null;
		try{
			RateCalculateController rateCalculatorController = context.getBean(RateCalculateController.class);
			logger.debug(">>> logger " + rateCalculatorController);
			Object result = rateCalculatorController.calculateRate(pathString, Double.valueOf(value));
			msg = result.toString();
		}catch(IllegalArgumentException ill){
			logger.error("Problems with parameters : ", ill);
			msg = ill.getMessage();
		}
		return msg;
	}
}
