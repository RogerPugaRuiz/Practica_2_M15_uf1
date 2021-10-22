/*
 * github repository:  https://github.com/RogerPugaRuiz/Au_Usuarios.git
 */
package login_logout;

import java.util.Random;
import java.util.Scanner;
import login_logout.Exception.LoginException;
import login_logout.Exception.NameLastNameException;
import login_logout.Exception.UserAlreadyExistException;
import static login_logout.Menus.*;


/**
 * Classe con un showLogin de usuario y funciones de administrador de usuarios.
 * @author roger
 */
public class AutenticacionUsuarios{
    /**
     * Conjunto de usuarios.
     */
    public static Usuarios usuarios = new Usuarios();

    /**
     * nombre del archivo json.
     */
    public static final String JSONFILE = "Usuarios.json";
    
    /**
     * Posibles mensajes de error.
     */
    public static final String[] ERRORCOMPRENSION = {
        "No te entiendo, puedes repetir que opcion prefieres",
        "Intenta con algo diferente como un numero",
        "Lo siento pero esto esta fuera de mi programacion"};
    
    /**
     * Metodo principal.
     * @param args 
     */
    public static void main(String[] args) {
        
        if (!run()) {
            System.out.println("Imposible ejecutar el programa");
        }
    }

    /**
     * Metodo principal de ejecución del programa
     *
     * @return Si el metodo run funciona con normalidad
     */
    public static boolean run() {
        try {
            // cargar los datos guardados en los archivos bin y json.
            // create a phrase for crypting
            loadData();
            RandomPhrase.generateRandomPhrase(200);

            int option = 0;
            do {
                option = showMenu(MENU);
                switch (option) {
                    case 0: // exit
                        option = exit();
                        break;
                    case 1: // login
                        showLogin();
                        break;
                }
            } while (option != 0);

        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Cargar los archivos json y bin.
     */
    public static void loadData(){

  
        // leer los usuarios guardado en archivo.bin y crearlos.
        Bin bin = new Bin();
        bin.read(usuarios);

        // importar archivos json de Usuarios.json
        jsonImport();

        // guardar los nuevos jugadores en archivo.bin
        bin.addList(usuarios);
    }

    /**
     * Metodo para leer y crear Usuario del archivo Usuarios.json.
     */
    public static void jsonImport(){
        // importar archivos json de Usuarios.json

        try {
            Json json = new Json(RandomPhrase.getPhrase());
            usuarios.addAll(json.jsonImport(JSONFILE));
            System.out.println(usuarios.getAll());
            System.out.printf("Usuarios importados de %s\n", JSONFILE);
        } catch (java.lang.NullPointerException npe) {
            System.out.printf("No existe el archivo %s\n", JSONFILE);
        }
    }

    /**
     * Metodo para salir de la aplicación final
     *
     * @param bin Classe bin para gestionar los archivos binarios
     * @return si salir o no de la aplicación final
     */
    public static int exit() {
        Bin bin = new Bin();
        bin.addList(usuarios);
        return 0;
    }

    /**
     * Metodo para leer el menu principal del showLogin
     *
     * @return La opcion del usuario
     */
    public static int showMenu(String[] MENU) {
        for (int i = 0; i < MENU.length; i++) {
            menuFormat(i, MENU[i]);
        }
        return scan();
    }

    /**
     * Metodo para escanear por pantalla una opcion
     *
     * @return la opcion y -1 si no es un numero.
     */
    public static int scan() {
        //        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int option = -1;
        System.out.print("--> ");
        try {
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
        } catch (Exception e) {
            Random rand = new Random();
            System.out.println(ERRORCOMPRENSION[rand.nextInt(ERRORCOMPRENSION.length)]);
        }
        return option;
    }

    /**
     * Metodo para dar formato al menu
     *
     * @param i el numero que representa la opcion
     * @param menu el texto de la opcion
     */
    public static void menuFormat(int i, String menu) {
        System.out.printf("%d. %s\n", i, menu);
    }

    /**
     * Metodo para leer el menu del usuario normal
     *
     * @return La opcion del usuario
     */
    /**
     * Metodo en el que se pide el e-mail y la contraseña del usuario.
     */
    public static void showLogin() {
        try {
            final Scanner SCANNER = new Scanner(System.in);
            System.out.print("e-mail: ");
            String email = SCANNER.next();
            
            System.out.print("password: ");
            String password = SCANNER.next();
            
            login(email, password);
        } catch (LoginException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    /**
     * Metodo que valida el login
     * @param email
     * @param password 
     * @throws login_logout.Exception.LoginException 
     */
    public static void login(String email, String password) throws LoginException{
        try {
            Usuario loginUser = usuarios.login(email, password);
            System.out.println(loginUser.getAll());

            switch (loginUser.getRol()) {
                case "admin": // user is admin
                    admin(loginUser);
                    break;
                case "user": // only user
                    user(loginUser);
                    break;
            }

        } catch (java.lang.NullPointerException npe) {
            throw new LoginException("e-mail o contraseña incorrecta");
        }
    }

    /**
     * Metodo para gestionar las funciones del admin - Como ver la informacion
     * de su session - Funciones CRUD (create, read, update, and delete); -
     * Salir de la session - Cerrar la aplicacion
     *
     * @param loginUser
     */
    public static void admin(Usuario loginUser) {
        int option = 0;
        do {
            option = showMenu(MENUADMIN);
            switch (option) {
                case 0: // exit
                    forceExit();
                    break;
                case 1: // get user information
                    System.out.println(loginUser.getAll());
                    break;
                case 2: // Create, Read, Update and Delete Methods
                    crud();
                    break;
                case 3: // export with json file
                    jsonExport();
                    break;
                case 4: // logout
                    option = exit();
                    break;
            }
        } while (option != 0);
    }
    
    /**
     * Metodo para exportar json con el nombre del archivo que elija el usuario.
     */
    public static void jsonExport() {
        final Scanner SCANNER = new Scanner(System.in);
        System.out.println("(¡NO ES NECESARIO AÑADIR LA EXTENSION!)\nnombre del archivo json: ");
        Json json = new Json(RandomPhrase.getPhrase());
        json.jsonExport(SCANNER.next() + ".json", usuarios);
    }

    /**
     * Metodo para controlar las opciones de C.R.U.D de los usuarios.
     */
    public static void crud() {
        int option;
        do {
            option = showMenu(CRUD);
            switch (option) {
                case 0: // exit
                    option = exit();
                    break;
                case 1: 
                    try {
                        // create new User
                        create();
                    } catch (NameLastNameException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case 2: // read with diferents options
                    read();
                    break;
                case 3: // user update
                    update();
                    break;
                case 4: // user delete
                    delete();
                    break;

            }
        } while (option != 0);
    }
    /**
     * Metodo para actualizar elementos de los Usuarios.
     */
    public static void update(){
        int option;
        do{
            option = showMenu(ATTRIBUTES);
            switch(option){
                case 0:
                    option = exit();
                    break;
                case 1:
                    updateName();
                    break;
                case 2:
                    updateLastname();
                    break;
                case 3:
                    updatePassword();
                    break;
                case 4:
                    updateRol();
                    break;
            }
            
        }while(option != 0);
    }
    
    /**
     * Metodo para actualizar el nombre de diferentes usuarios.
     */
    public static void updateName(){
        final Scanner SC = new Scanner(System.in); 
        System.out.print("Nombre: ");
        String nombre = SC.next();
        
        System.out.print("E-mail: ");
        String email = SC.next();
        Usuario u = usuarios.search(email);
        
        u.setNombre(nombre);
    }
    
    
    /**
     * Metodo para actualizar el apellido de diferentes usuarios.
     */
    public static void updateLastname(){
        final Scanner SC = new Scanner(System.in);
        System.out.print("Apellidos: ");
        String lastname = SC.nextLine();
        
        System.out.println("E-mail: ");
        String email = SC.next();
        Usuario u = usuarios.search(email);
        
        u.setApellidos(lastname); 
    }
    
    /**
     * Metodo para actualizar la contraseña de diferentes usuarios.
     */
    public static void updatePassword(){
        final Scanner SC = new Scanner(System.in);
        System.out.print("Password: ");
        String password = SC.next();
        
        System.out.print("E-mail: ");
        String email = SC.next();
        Usuario u = usuarios.search(email);
        
        u.setPassword(password);
    }
    
    /**
     * Metodo para actualizar el rol de diferentes usuarios.
     */
    public static void updateRol(){
        final Scanner SC = new Scanner (System.in);
        System.out.print("Rol: ");
        String rol = SC.next();
        
        System.out.print("E-mail: ");
        String email = SC.next();
        Usuario u = usuarios.search(email);
        if (rol.equalsIgnoreCase("admin")){
            u.setRol(Usuario.ADMIN);
        }else{
            u.setRol(Usuario.USER);
        }
   
    }
    
    
    /**
     * Metodo para eliminar usuario.
     */
    public static void delete() {
        final Scanner SCANNER = new Scanner(System.in);
        
        System.out.print("E-mail: ");
        
        String s = SCANNER.nextLine();
        s = s.replace(" ", "");
        String[] emails = s.split(",");
        for (String email : emails){
            Usuario usuario = usuarios.search(email);
            System.out.printf("Estas intentando eliminar a %s %s, estas  seguro (Si,No)\n",usuario.getNombre(),usuario.getApellidos());
            char option = SCANNER.next().charAt(0);
            if (Character.toLowerCase(option) == 's' || Character.toLowerCase(option) == 'y'){
                if (usuarios.remove(usuario)) {
                    System.out.printf("%s %s fue eliminado permanentemente\n", usuario.getNombre(), usuario.getApellidos());
                }else{
                    System.out.printf("%s $s no existe como usuario\n", usuario.getNombre(), usuario.getApellidos());
                }
            }else{
                System.out.printf("%s %s no fue eliminado por no confirmar la operación\n",usuario.getNombre(),usuario.getApellidos());
            }            
        }

    }

    /**
     * Metodo para leer los usuarios segun algunos parametros.
     */
    public static void read() {
        final Scanner SCANNER = new Scanner(System.in);
        
        int option=-1;
        do {
            try {
                option = showMenu(READ);
                switch (option) {
                    case 0:
                        option = exit();
                        break;
                    case 1:
                        System.out.println(usuarios.getAll());
                        System.out.printf("Numero de usuarios: %d\n", usuarios.count());
                        break;
                    case 2:
                        System.out.print("E-mail: ");
                        String email = SCANNER.next();
                        System.out.println(usuarios.search(email).getAll());
                        break;
                    case 3:
                        System.out.print("Nombre: ");
                        String nombre = SCANNER.next();
                        Usuarios userSearch = usuarios.searchAll(nombre);
                        System.out.println(userSearch.getAll());
                        System.out.printf("Numero de usuarios: %d encontrados\n", userSearch.count());
                        break;
                    case 4:
                        System.out.print("Nombre: ");
                        nombre = SCANNER.next();
                        System.out.print("Apellido: ");
                        SCANNER.nextLine();
                        String apellido = SCANNER.nextLine();
                        userSearch = usuarios.searchAll(nombre, apellido);
                        System.out.println(userSearch.getAll());
                        System.out.printf("Numero de usuario: %d encontrados\n", userSearch.count());
                        break;
                        
                }
            } catch (UserAlreadyExistException ex) {
                System.out.println(ex.getMessage());
            }
        } while (option != 0);

    }

    /**
     * Metodo para crear un usuario.
     */
    public static void create() throws NameLastNameException {
        final Scanner SCANNER = new Scanner(System.in);
        
        String nombre;
        String apellidos;
        String email;
        String password;
        int rol;
        
        
        System.out.println("nombre: ");
        nombre = SCANNER.next();

        System.out.println("apellido: ");
        SCANNER.nextLine(); // para poder capturar una linea con espacios
        apellidos = SCANNER.nextLine();

        System.out.println("email: ");
        email = SCANNER.next();

        System.out.println("password: ");
        password = SCANNER.next();

        System.out.println("rol(user/admin): ");
        String rolStr = SCANNER.next();
        rol = Usuario.USER;
        if (rolStr.equalsIgnoreCase("admin")) {
            rol = Usuario.ADMIN;
        }
        
        if (containNumbers(nombre)){
            throw new NameLastNameException("Nombre no puede contener numeros");
        }else if (containNumbers(apellidos)){
            throw new NameLastNameException("Apellido no puede contener numeros");
        }else{
            try {
                usuarios.add(new Usuario(nombre, apellidos, email, password, rol));
            } catch (UserAlreadyExistException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Metodo para gestionar las funciones del usuario - Como ver la informacion
     * de su session - Salir de la session - Cerrar la aplicacion
     *
     * @param loginUser
     */
    public static void user(Usuario loginUser) {
        int option = 0;
        do {
            option = showMenu(MENUUSER);
            switch (option) {
                case 0: // exit
                    forceExit();
                    break;
                case 1: // show info
                    System.out.println(loginUser.getAll());
                    break;
                case 2: // logout
                    option = exit();
                    break;
                case 3:
                    option=showMenu(MENUADN);
                    int opADN=0;
                    switch(opADN){
                        case 0:
                            opADN=exit();
                        case 1:
                            opADN= darVueltaCadenaADN();
                            break;
                        case 2:
                            opADN= baseMasRepetida();
                            break;
                        case 3:
                            opADN = baseMenosRepetida();
                            break;
                        case 4:
                            opADN =mostrarBases();
                            break;
                        case 5:
                            opADN = convertADNtoARN();
                            break;
                        case 6:
                            opADN = convertARNtoADN();
                            break;
                    }
                    
                    
            }
        } while (option != 0);
        return;
    }

    /**
     * Metodo para forzar la salida del programa.
     */
    public static void forceExit() {
        // guardar los usuarios en archivos.bin y cerrar la aplicación
        Bin bin = new Bin();
        bin.addList(usuarios);
        System.exit(0);
    }

    private static boolean containNumbers(String str) {
        return 
            str.contains("1") || 
            str.contains("2") || 
            str.contains("3") || 
            str.contains("4") ||
            str.contains("5") || 
            str.contains("6") || 
            str.contains("7") || 
            str.contains("8") ||
            str.contains("9") ||
            str.contains("0");
    }

    private static int darVueltaCadenaADN() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static int baseMasRepetida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static int baseMenosRepetida() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static int mostrarBases() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static int convertADNtoARN() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static int convertARNtoADN() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
