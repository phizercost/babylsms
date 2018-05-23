package com.phizercost.babylsms.utils;

public enum PrefixCountryCode {
	
	COUNTRY_CODE("250"), 
	MSISDN_LENGTH("9"), 
	MTN_PREFIX("78"), 
	TIGO_PREFIX("72"), 
	AIRTEL_PREFIX("73"), 
	LOCAL_PREFIX("0"), 
	ALLOWED_TEST_SENDER("BABYL");
	
	private final String string;
	
	private PrefixCountryCode(String string)
	{
		this.string = string;
	}
	
	public String getString(){
		return this.string;
	}

}
