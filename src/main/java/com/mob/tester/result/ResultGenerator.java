package com.mob.tester.result;

import org.apache.velocity.VelocityContext;

public class ResultGenerator {

	
	
	void generateresult(){
		
		VelocityContext context = new VelocityContext();
		
		context.put("result", ResultHolder.scenarioResultHolder);
		
		
	}
	
	
	
	
}
