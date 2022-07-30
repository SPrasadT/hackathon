package com.mob.tester.scenario.strategy;

import java.util.HashMap;
import java.util.Map;

import com.mob.tester.scenario.Scenario1;
import com.mob.tester.scenario.Scenario2;
import com.mob.tester.scenario.Scenario3;
import com.mob.tester.scenario.Scenario4;

public class ScenatioStrategyHolder {


	public static Map<String,ScenarioStrategy> allScenarioHolder=new HashMap<String,ScenarioStrategy>();
	
	
	public ScenatioStrategyHolder(){
		allScenarioHolder.put("Scenario1", new Scenario1());
		allScenarioHolder.put("Scenario2", new Scenario2());
		allScenarioHolder.put("Scenario3", new Scenario3());
		allScenarioHolder.put("Scenario4", new Scenario4());
		
	}
	
	
}
