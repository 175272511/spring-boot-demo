package com.example.designmode.company.factory_strategy;

/**
 * @author cbf4Life cbf4life@126.com
 * I'm glad to share my knowledge with you all.
 * 策略登记
 */
public enum StrategyMan {
	
	SteadyDeduction("com.example.designmode.company.SteadyDeduction"),
	FreeDeduction("com.example.designmode.company.FreeDeduction");
	
	String value = "";
	private StrategyMan(String _value){
		this.value = _value;
	}
	
	public String getValue(){
		return this.value;
	}
}
