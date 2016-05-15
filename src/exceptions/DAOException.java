package exceptions;

public class DAOException extends RuntimeException{
	private static final long serialVersionUID = 7454252193668273515L;

	public DAOException( String message ) {
        super( message );
    }

    public DAOException( String message, Throwable cause ) {
        super( message, cause );
    }

    public DAOException( Throwable cause ) {
        super( cause );
    }
}
