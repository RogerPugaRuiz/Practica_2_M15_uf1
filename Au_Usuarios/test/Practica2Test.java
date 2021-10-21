

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import login_logout.AutenticacionUsuarios;
import login_logout.Usuario;
import login_logout.Usuarios;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author roger
 */
public class Practica2Test {
    public Practica2Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void loginTest(){
        Usuarios users = new Usuarios();
        final Usuario EXPECTED_USER = new Usuario(
                "Roger",
                "Puga",
                "roger@gmail.com",
                "1234", Usuario.USER);
        users.add(EXPECTED_USER);
        Usuario user_login = users.login("roger@gmail.com", "1234");
        Boolean condition = user_login.equals(EXPECTED_USER);
        assertTrue("Login Correcto",condition);
    }
    @Test
    public void createNewUserTest(){
        
    }
}
