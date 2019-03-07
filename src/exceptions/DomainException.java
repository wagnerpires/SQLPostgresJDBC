package exceptions;

import java.sql.SQLException;

public class DomainException extends SQLException {
	private static final long serialVersionUID = 1L;

	public DomainException(String msg) {
		super(msg);
	}
}