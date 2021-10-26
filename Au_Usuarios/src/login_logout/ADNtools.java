/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_logout;

import login_logout.Exception.ReversedAdnException;

/**
 *
 * @author albertcasany
 */
public class ADNtools {
    private final String[] bases = {
            "A",
            "G",
            "C",
            "T"}; 

    /**
     * Method to reversed the string adn
     * @param adn
     * @return 
     */
    public String reversed(String adn) throws ReversedAdnException{
        boolean validate = false;
        adn = adn.toUpperCase();
        StringBuilder sb = new StringBuilder();
        for (int i = adn.length()-1; i >= 0; i--){
            for (int j = 0;j < bases.length; j++){
                if (bases[j].equals(adn.charAt(i))){
                    validate = true;
                }
            }
            sb.append(adn.charAt(i));
            if (validate == false){
                throw new ReversedAdnException("error en la posicion " + i);
            }
        }

        return sb.toString();
    }
}
