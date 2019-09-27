package mobiquityinc.packer.exception;

public class APIException extends RuntimeException{

    public APIException(String message) {
        super(message);
    }

    public APIException(String message, Throwable cause) {
        super(message, cause);
    }

    public static void checkCondition(boolean condition, String message) {
        if (!condition) throw new APIException(message);
    }
}
