package cz.muni.fi.pa165.esports.exceptions;

/**
 * @author Gabriela Kandova
 */
public class EsportsServiceException extends RuntimeException {
    public EsportsServiceException() {
        super();
    }

    public EsportsServiceException(String message, Throwable cause,
                                   boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public EsportsServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public EsportsServiceException(String message) {
        super(message);
    }

    public EsportsServiceException(Throwable cause) {
        super(cause);
    }
}
