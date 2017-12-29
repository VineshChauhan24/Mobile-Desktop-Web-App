package comcast.test.config.configServices;

import java.util.ResourceBundle;

public class DataServiceProperties {

	// PreConfiguration Properties.
	public static final String BROWSER;
	public static final String APPURL;
	public static final String HOMEAPPURL;

	// Login Credentials Properties
	public static final String _USER_NAME;
	public static final String _PASSWORD;
	public static final String _RESET_PASSWORD;
	public static final String _CONFIRM_PASSWORD;
	public static final String _DIFF_CONFIRM_PASSWORD;

	// InVlaid Login Credentials Properties
	public static final String _INVALID_USER_NAME;
	public static final String _INVALID_PASSWORD;
	public static final String _INVALID_PASSWORD_LENGTH;
	public static final String _INVALID_EMAIL;
	public static final String _INVALID_MOBILE_NUMBER;
	public static final String _NONCAPITAL_LETTER_PASSWORD;
	public static final String _NONDIGIT_PASSWORD;
	public static final String _INVALID_ZIP_LENGTH;
	public static final String _INVALID_ZIP;

	// User Registration Properties
	public static final String _FIRST_NAME;
	public static final String _LAST_NAME;
	public static final String _ADDRESS;
	public static final String _CITY;
	public static final String _STATE;
	public static final String _ZIP;
	public static final String _EMAIL;
	public static final String _MOBILE_NUMBER;
	public static final String _REG_PASSWORD;

	// Updated User Account Details Properties
	public static final String _UPD_FIRST_NAME;
	public static final String _UPD_LAST_NAME;
	public static final String _UPD_ADDRESS;
	public static final String _UPD_CITY;
	public static final String _UPD_STATE;
	public static final String _UPD_ZIP;
	public static final String _UPD_EMAIL;
	public static final String _UPD_MOBILE_NUMBER;

	// User Account Details Properties
	public static final String _CC_NUMBER;
	public static final String _CC_EXPIRY_MONTH;
	public static final String _CC_SECURITY_CODE;
	public static final String _CC_YEAR;
	public static final String _CC_PIN_CODE;
	public static final String _ACTIVATION_CODE;

	// User Account Details Properties
	public static final String _UPD_CC_NUMBER;
	public static final String _UPD_CC_EXPIRY_MONTH;
	public static final String _UPD_CC_SECURITY_CODE;
	public static final String _UPD_CC_PIN_CODE;
	public static final String _UPD_CC_YEAR;
	public static final String _UPD_ACTIVATION_CODE;

	// Invalid User Account Details Properties
	public static final String _INVALID_CC_PIN_CODE;
	public static final String _INVALID_CC_PIN_CODE_LENGTH;
	public static final String _INVALID_CC_NUMBER;
	public static final String _INVALID_CC_SECURITY_CODE_CHAR;
	public static final String _INVALID_CC_SECURITY_CODE;
	public static final String _INVALID_CC_SECURITY_CODE_LENGTH;
	public static final String _INVALID_CC_EXPIRY_MONTH;
	public static final String _INVALID_ACTIVATION_CODE;

	// Video Search Properties
	public static final String _SEARCH_VIDEO_BY;
	public static final String _VIDEIO_KEY_NAME;

	// TestCases Specific Properties.
	public static final String _CURRENT_YEAR;
	public static final String _PAST_YEAR;

	// Invalid User Name
	public static final String _SQLKEY_USERNAME;
	public static final String _SQLSTMT_USERNAME;
	public static final String _JS_USERNAME;
	public static final String _SPL_USERNAME;
	public static final String _DIGIT_USERNAME;

	static {
		ResourceBundle rsbundle = ResourceBundle.getBundle("data");

		// GET Configuration Properties Values.
		BROWSER = rsbundle.getString("BROWSER");
		APPURL = rsbundle.getString("APPURL");
		HOMEAPPURL = rsbundle.getString("HOMEAPPURL");

		// GET Login Credentials Properties Values.
		_USER_NAME = rsbundle.getString("USER_NAME");
		_PASSWORD = rsbundle.getString("PASSWORD");
		_RESET_PASSWORD = rsbundle.getString("RESET_PASSWORD");
		_CONFIRM_PASSWORD = rsbundle.getString("CONFIRM_PASSWORD");
		_DIFF_CONFIRM_PASSWORD = rsbundle.getString("DIFF_CONFIRM_PASSWORD");

		// GET Invalid Login Credentials Properties Values.
		_INVALID_USER_NAME = rsbundle.getString("INVALID_USER_NAME");
		_INVALID_PASSWORD = rsbundle.getString("INVALID_PASSWORD");
		_INVALID_PASSWORD_LENGTH = rsbundle
				.getString("INVALID_PASSWORD_LENGTH");
		_INVALID_EMAIL = rsbundle.getString("INVALID_EMAIL");
		_INVALID_MOBILE_NUMBER = rsbundle.getString("INVALID_MOBILE_NUMBER");
		_NONCAPITAL_LETTER_PASSWORD = rsbundle
				.getString("NONCAPITAL_LETTER_PASSWORD");
		_NONDIGIT_PASSWORD = rsbundle.getString("NONDIGIT_PASSWORD");
		_INVALID_ZIP_LENGTH = rsbundle.getString("INVALID_ZIP_LENGTH");
		_INVALID_ZIP = rsbundle.getString("INVALID_ZIP");

		// GET Registration Properties Values.
		_FIRST_NAME = rsbundle.getString("FIRST_NAME");
		_LAST_NAME = rsbundle.getString("LAST_NAME");
		_ADDRESS = rsbundle.getString("ADDRESS");
		_CITY = rsbundle.getString("CITY");
		_STATE = rsbundle.getString("STATE");
		_ZIP = rsbundle.getString("ZIP");
		_EMAIL = rsbundle.getString("EMAIL");
		_MOBILE_NUMBER = rsbundle.getString("MOBILE_NUMBER");
		_REG_PASSWORD = rsbundle.getString("REG_PASSWORD");

		// GET Updated Properties Values.
		_UPD_FIRST_NAME = rsbundle.getString("UPD_FIRST_NAME");
		_UPD_LAST_NAME = rsbundle.getString("UPD_LAST_NAME");
		_UPD_ADDRESS = rsbundle.getString("UPD_ADDRESS");
		_UPD_CITY = rsbundle.getString("UPD_CITY");
		_UPD_STATE = rsbundle.getString("UPD_STATE");
		_UPD_ZIP = rsbundle.getString("UPD_ZIP");
		_UPD_EMAIL = rsbundle.getString("UPD_EMAIL");
		_UPD_MOBILE_NUMBER = rsbundle.getString("UPD_MOBILE_NUMBER");

		// GET Account Details Properties Values.
		_CC_NUMBER = rsbundle.getString("CC_NUMBER");
		_CC_EXPIRY_MONTH = rsbundle.getString("CC_EXPIRY_MONTH");
		_CC_SECURITY_CODE = rsbundle.getString("CC_SECURITY_CODE");
		_CC_PIN_CODE = rsbundle.getString("CC_PIN_CODE");
		_CC_YEAR = rsbundle.getString("CC_YEAR");
		_ACTIVATION_CODE = rsbundle.getString("ACTIVATION_CODE");

		// GET Update Account Details Properties Values.
		_UPD_CC_NUMBER = rsbundle.getString("CC_NUMBER");
		_UPD_CC_EXPIRY_MONTH = rsbundle.getString("UPD_CC_EXPIRY_MONTH");
		_UPD_CC_SECURITY_CODE = rsbundle.getString("UPD_CC_SECURITY_CODE");
		_UPD_CC_PIN_CODE = rsbundle.getString("UPD_CC_PIN_CODE");
		_UPD_CC_YEAR = rsbundle.getString("UPD_CC_YEAR");
		_UPD_ACTIVATION_CODE = rsbundle.getString("UPD_ACTIVATION_CODE");

		// GET Invalid Account Details Properties Values.
		_INVALID_CC_PIN_CODE = rsbundle.getString("INVALID_CC_PIN_CODE");
		_INVALID_CC_PIN_CODE_LENGTH = rsbundle
				.getString("INVALID_CC_PIN_CODE_LENGTH");
		_INVALID_CC_NUMBER = rsbundle.getString("INVALID_CC_NUMBER");
		_INVALID_CC_SECURITY_CODE_CHAR = rsbundle
				.getString("INVALID_CC_SECURITY_CODE_CHAR");
		_INVALID_CC_SECURITY_CODE = rsbundle
				.getString("INVALID_CC_SECURITY_CODE");
		_INVALID_CC_SECURITY_CODE_LENGTH = rsbundle
				.getString("INVALID_CC_SECURITY_CODE_LENGTH");
		_INVALID_CC_EXPIRY_MONTH = rsbundle
				.getString("INVALID_CC_EXPIRY_MONTH");
		_INVALID_ACTIVATION_CODE = rsbundle
				.getString("INVALID_ACTIVATION_CODE");

		// GET Video Search Properties Values.
		_SEARCH_VIDEO_BY = rsbundle.getString("SEARCH_VIDEO_BY");
		_VIDEIO_KEY_NAME = rsbundle.getString("VIDEIO_KEY_NAME");

		// GET TestCase Specific Properties Values.
		_CURRENT_YEAR = rsbundle.getString("CURRENT_YEAR");
		_PAST_YEAR = rsbundle.getString("PAST_YEAR");

		// GET All Invalid User Name Properties Values.
		_DIGIT_USERNAME = rsbundle.getString("DIGIT_USERNAME");
		_SQLKEY_USERNAME = rsbundle.getString("SQLKEY_USERNAME");
		_SQLSTMT_USERNAME = rsbundle.getString("SQLSTMT_USERNAME");
		_JS_USERNAME = rsbundle.getString("JS_USERNAME");
		_SPL_USERNAME = rsbundle.getString("SPL_USERNAME");
	}
}
