import co.edu.uniquindio.ingsoftwareproject.services.implement.LoginServiceImpl;
import co.edu.uniquindio.ingsoftwareproject.services.interfaces.LoginService;
import org.junit.jupiter.api.Test;

public class LoginTest {

    LoginService loginService = new LoginServiceImpl();

    @Test
    public void login() throws Exception {


        String response = loginService.login("jaimito1","0000");
        System.out.println(response);


    }

}
