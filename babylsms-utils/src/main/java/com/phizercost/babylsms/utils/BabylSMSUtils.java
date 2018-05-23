package com.phizercost.babylsms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BabylSMSUtils {

	public static boolean isMsisdnValid(String msisdn) {

		final int msisdnNationalFormatValidLength = Integer.parseInt(PrefixCountryCode.MSISDN_LENGTH.getString());

		final String msisdnLocalPrefix = PrefixCountryCode.LOCAL_PREFIX.getString();
		
		final String countryCode = PrefixCountryCode.COUNTRY_CODE.getString();
		String msisdnNationalOperatorPrefixMTN = PrefixCountryCode.MTN_PREFIX.getString();
		String msisdnNationalOperatorPrefixTigo = PrefixCountryCode.TIGO_PREFIX.getString();
		String msisdnNationalOperatorPrefixAirtel = PrefixCountryCode.AIRTEL_PREFIX.getString();

		if (msisdn.matches("[+]" + countryCode + msisdnNationalOperatorPrefixMTN + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixMTN.length()) + "}"))
			return true;
		else if (msisdn.matches(countryCode + msisdnNationalOperatorPrefixMTN + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixMTN.length()) + "}"))
			return true;
		else if (msisdn.matches(msisdnLocalPrefix + msisdnNationalOperatorPrefixMTN + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixMTN.length()) + "}"))
			return true;
		else if (msisdn.matches(msisdnNationalOperatorPrefixMTN + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixMTN.length()) + "}"))
			return true;
		else if (msisdn.matches("0" + msisdnNationalOperatorPrefixMTN + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixMTN.length()) + "}"))
			return true;
		else if (msisdn.matches("[+]" + countryCode + msisdnNationalOperatorPrefixTigo + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixTigo.length()) + "}"))
			return true;
		else if (msisdn.matches(countryCode + msisdnNationalOperatorPrefixTigo + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixTigo.length()) + "}"))
			return true;
		else if (msisdn.matches(msisdnLocalPrefix + msisdnNationalOperatorPrefixTigo + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixTigo.length()) + "}"))
			return true;
		else if (msisdn.matches(msisdnNationalOperatorPrefixTigo + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixTigo.length()) + "}"))
			return true;
		else if (msisdn.matches("0" + msisdnNationalOperatorPrefixTigo + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixTigo.length()) + "}"))
			return true;
		/*else if (msisdn.matches("[+]" + countryCode + msisdnNationalOperatorPrefixAirtel + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixAirtel.length()) + "}"))
			return true;
		else if (msisdn.matches(countryCode + msisdnNationalOperatorPrefixAirtel + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixAirtel.length()) + "}"))
			return true;
		else if (msisdn.matches(msisdnLocalPrefix + msisdnNationalOperatorPrefixAirtel + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixAirtel.length()) + "}"))
			return true;
		else if (msisdn.matches(msisdnNationalOperatorPrefixAirtel + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixAirtel.length()) + "}"))
			return true;
		else if (msisdn.matches("0" + msisdnNationalOperatorPrefixAirtel + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixAirtel.length()) + "}"))
			return true;*/
		else
			return false;
	}

	public static boolean isReceiverMsisdnValid(String msisdn) {

		final int msisdnNationalFormatValidLength = Integer.parseInt(PrefixCountryCode.MSISDN_LENGTH.getString());

		final String countryCode = PrefixCountryCode.COUNTRY_CODE.getString();
		String msisdnNationalOperatorPrefixMTN = PrefixCountryCode.MTN_PREFIX.getString();
		String msisdnNationalOperatorPrefixTigo = PrefixCountryCode.TIGO_PREFIX.getString();
		String msisdnNationalOperatorPrefixAirtel = PrefixCountryCode.AIRTEL_PREFIX.getString();
		if (msisdn.matches(countryCode + msisdnNationalOperatorPrefixMTN + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixMTN.length()) + "}"))
			return true;
		else if (msisdn.matches(countryCode + msisdnNationalOperatorPrefixTigo + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixTigo.length()) + "}"))
			return true;
		else if (msisdn.matches(countryCode + msisdnNationalOperatorPrefixAirtel + "[0-9]{"
				+ (msisdnNationalFormatValidLength - msisdnNationalOperatorPrefixAirtel.length()) + "}"))
			return true;
		else
			return false;
	}

	public static boolean isStringEmpty(String string) {

		if (string != null && !string.isEmpty())
			return false;
		else
			return true;
	}
	
	public static boolean isDateGreaterThanToday(String string){
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
		Date date;
		try {
			date = sdf.parse(string);
			Date dateNow = new Date();
	        if(date.before(dateNow))
	        	return false;
	        else
	        	return true;
		} catch (ParseException e) {
			System.out.println(e.toString());
			return false;
		}
        
		
	}
}
