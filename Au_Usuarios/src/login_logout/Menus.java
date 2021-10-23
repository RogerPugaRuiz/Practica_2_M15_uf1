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
public class Menus {
    
    public static final String[] MENUADN = {
        "Exit",
        "Donar la volta a una cadena de ADN ",
        "Trobar la base més repetida",
        "Trobar la base menys repetida",
        "Fer recompte de bases i mostrarles.",
        "Convertir ARN a ADN.",
        "Convertir ARN a ADN"};
    
    
    /**
     * Menu principal.
     */
    public static final String[] MENU = {
        "EXIT",
        "LOGIN"};
    
    /**
     * Menu del usuario.
     */
    public static final String[] MENUUSER = {
        "EXIT",
        "INFO. USER",
        "LOGOUT",
        "MANAGE ARRAYS ADN/ARN"};
    
    /**
     * Menu del administrador.
     */
    public static final String[] MENUADMIN = {
        "EXIT",
        "INFO. USER",
        "C.R.U.D (Create, Read, Update and Delete)",
        "JSON EXPORT",
        "JSON IMPORT",
        "LOGOUT"};
    
    /**
     * Menu opciones CRUD.
     */
    public static final String[] CRUD = {
        "EXIT",
        "CREATE",
        "READ",
        "UPDATE",
        "DELETE"};
    
    /**
     * Menu de lectura.
     */
    public static final String[] READ = {
        "EXIT",
        "FULL",
        "SEARCH BY E-MAIL",
        "SEARCH BY NAME",
        "SEARCH BY NAME AND LASTNAME"};
    
    /**
     * Menu de los atributos de usuario.
     */
    public static final String[] ATTRIBUTES = {
        "EXIT",
        "NAME",
        "LASTNAME",
        "PASSWORD",
        "ROL"};
}
