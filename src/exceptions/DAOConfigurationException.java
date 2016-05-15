package exceptions;

public class DAOConfigurationException extends RuntimeException{
	private static final long serialVersionUID = 5854584354971412238L;

	public DAOConfigurationException( String message ) {
        super( message );
    }

    public DAOConfigurationException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOConfigurationException( Throwable cause ) {
        super( cause );
    }
}
