/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package login_logout.Exception;

/**
 *
 * @author roger
 */
public class InvalidDNAException extends Exception{

    /**
     * Creates a new instance of <code>ReversedAdnException</code> without
     * detail message.
     */
    public InvalidDNAException() {
    }

    /**
     * Constructs an instance of <code>ReversedAdnException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidDNAException(String msg) {
        super(msg);
    }
}
