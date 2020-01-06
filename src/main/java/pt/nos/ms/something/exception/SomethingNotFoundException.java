package pt.nos.ms.something.exception;

public class SomethingNotFoundException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public SomethingNotFoundException() {
        super("Could not found the specified Something.");
    }

}
