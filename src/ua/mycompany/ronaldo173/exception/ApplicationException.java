package ua.mycompany.ronaldo173.exception;

public class ApplicationException extends Exception {
	private static final long serialVersionUID = 1L;

	public ApplicationException() {
	}

	public ApplicationException(String message) {
		super(message);
	}

	public ApplicationException(String message, Throwable e) {
		super(message, e);
	}
}