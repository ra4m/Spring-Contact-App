package com.raam.capp.util;

/**
 * This class contains utility methods related to String operations
 * @author Raam
 */

public class StringUtil {
    
    public static String toCommaSeparatedString(Object[] items) {
        StringBuilder sb = new StringBuilder();
        
        // add a comma after every array element
        for (Object item : items) {
            sb.append(item).append(",");
        }
        
        // remove the last comma
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        
        return sb.toString();
    }
    
    
    
    
}
