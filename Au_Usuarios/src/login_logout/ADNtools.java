/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_logout;

/**
 *
 * @author albertcasany
 */
public class ADNtools {
    public String reversed(String adn){
        StringBuilder sb = new StringBuilder();
        for (int i = adn.length(); i >= 0; i--){
            sb.append(adn.charAt(i));
        }
        return sb.toString();
    }
}
