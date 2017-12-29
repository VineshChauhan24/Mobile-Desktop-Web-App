package comcast.test.app.common.exception;

import java.io.IOException;

import comcast.test.config.configServices.utils.BaseTest;

public class ComcastTestException extends Exception {

	private static final long serialVersionUID = 1L;
	protected String className;
	protected int errorCode;
	protected String methodName;
	protected Exception exceptionRef;

	public ComcastTestException(int inErrorCode, String inClassName,
			String inMethodName, Exception inExceptionRef) {
		super(inClassName + " : " + inMethodName, inExceptionRef);
		this.errorCode = inErrorCode;
		this.className = inClassName;
		this.methodName = inMethodName;
		this.exceptionRef = inExceptionRef;
	}

	public ComcastTestException(String inClassName, String inMethodName,
			Exception inExceptionRef) throws IOException, InterruptedException {
		super(inClassName + " : " + inMethodName, inExceptionRef);
		this.className = inClassName;
		this.methodName = inMethodName;
		this.exceptionRef = inExceptionRef;
		BaseTest baseTest = new BaseTest();
		baseTest.captureScreenshot();
	}

	public ComcastTestException(String inClassName, String inMethodName,
			String errorMessage) {
		super(errorMessage);
		this.className = inClassName;
		this.methodName = inMethodName;
	}

}
