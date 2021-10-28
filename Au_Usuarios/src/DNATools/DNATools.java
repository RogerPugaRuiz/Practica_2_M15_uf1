/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DNATools;

import Exceptions.InvalidDNAException;

/**
 *
 * @author albertcasany
 */
public class DNATools {
    private final char[] bases = {
            'A',
            'G',
            'C',
            'T'}; 

    /**
     * Method to reversed the string adn
     * @param adn
     * @return 
     */
    public String reversed(String adn) throws InvalidDNAException {
        
        adn = adn.toUpperCase();
        String new_adn = "";
        
        for (int i = 0; i < adn.length(); i++){
            if (isValidAdn(adn.charAt(i))){
                new_adn = adn.charAt(i) + new_adn;

            }else{
                throw new InvalidDNAException("Error el la posicion " + i);
            }
            
        }
        

        return new_adn;
    }
    
    
    /**
     * Method to validate DNA
     * @param base
     * @return if is valid or invalid DNA format
     */
    public boolean isValidAdn(char base){
        // default sentense, DNA not valid
        boolean isTrue = false;
        for (int i=0; i<bases.length; i++){
            
            // if any letters are valid, the sentense are valid
            if (bases[i]==base){
                isTrue = true;
            }
        }
        return isTrue;
    }
}
