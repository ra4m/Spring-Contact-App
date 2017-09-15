package com.raam.capp.exception;

/**
 *
 * @author Raam
 */
public class UserBlockedException extends Exception{
    
    /**
     * Created User object without error description
     */
    public UserBlockedException() {
    }
    
   
    /**
     * Created User object with error description
     * @param errorDescription
     */
    public UserBlockedException(String errorDescription) {
        super(errorDescription);
    }
    
}
