package o2.co.uk.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {
	public static boolean validateEmail(String emailId){
		String pattern = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";

		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(emailId);

		return m.find();

	}
}
