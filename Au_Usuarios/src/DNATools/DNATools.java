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
     *
     * @param adn
     * @return
     */
    public String reversed(String adn) throws InvalidDNAException {

        adn = adn.toUpperCase();
        String new_adn = "";

        for (int i = 0; i < adn.length(); i++) {
            if (isValidAdn(adn.charAt(i))) {
                new_adn = adn.charAt(i) + new_adn;

            } else {
                throw new InvalidDNAException("Error el la posicion " + i);
            }

        }

        return new_adn;
    }

    /**
     * Method to validate DNA
     *
     * @param base
     * @return if is valid or invalid DNA format
     */
    public boolean isValidAdn(char base) {
        // default sentense, DNA not valid
        boolean isTrue = false;
        for (int i = 0; i < bases.length; i++) {

            // if any letters are valid, the sentense are valid
            if (bases[i] == base) {
                isTrue = true;
            }
        }
        return isTrue;
    }

    /**
     * 
     * @param cadena
     * @return the letter of the most repeated string.
     */
    public char baseMostRepeted(String cadena) {
        cadena = cadena.toUpperCase();
        char[] carctersADN = cadena.toCharArray();
        int contador = 0;
        char caracter = 0;
        int err = 0;

        for (char c : carctersADN) {
            err++;
            if (c == 'A' || c == 'T' || c == 'C' || c == 'G') {
                while (cadena.length() != 0) { // mientras la cadena tenga algún carácter la recorremos
                    int contadorAux = 0;
                    for (int j = 0; j < cadena.length(); j++) { // recorremos la cadena para contar los caracteres del indice cero
                        if (cadena.charAt(0) == cadena.charAt(j)) {
                            contadorAux++;
                        }
                    }
                    if (contadorAux > contador) { // si el contador del nuevo caracter es mayor al que ya estaba guardado, lo cambiamos
                        contador = contadorAux;
                        caracter = cadena.charAt(0);
                    }
                    // borramos los carácteres contados para ahorrar entrar mas veces para contarlos otra vez
                    cadena = cadena.replaceAll(cadena.charAt(0) + "", "");
                }
            } else {
                System.out.println("Error, el format de la cadena d’ADN no és vàlid. Caracter incorrecte a la posició " + err);
                System.exit(0);
            }
        }
        return caracter;
    }

    public char baseLeastRepeated(/*String cadena*/){
        char caracter = 0;
        return caracter;
    }
}
