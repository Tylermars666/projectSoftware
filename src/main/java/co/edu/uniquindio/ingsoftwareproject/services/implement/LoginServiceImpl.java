package co.edu.uniquindio.ingsoftwareproject.services.implement;

import co.edu.uniquindio.ingsoftwareproject.model.classes.Usuario;
import co.edu.uniquindio.ingsoftwareproject.model.enums.TipoAlerta;
import co.edu.uniquindio.ingsoftwareproject.repository.implement.UserRepoImpl;
import co.edu.uniquindio.ingsoftwareproject.repository.interfaces.UserRepo;
import co.edu.uniquindio.ingsoftwareproject.services.interfaces.LoginService;
import co.edu.uniquindio.ingsoftwareproject.user.Alertas;
import co.edu.uniquindio.ingsoftwareproject.user.UsuarioLoggeado;

public class LoginServiceImpl implements LoginService {

    UserRepo userRepo = new UserRepoImpl();


    @Override
    public String login(String username, String password) throws Exception {

        if(username.isBlank() || password.isBlank()){

            Alertas.alertar(TipoAlerta.ERROR,"Error de datos","No se llenaron todos los campos del login");
            throw new Exception("No se llenaron todos los campos del login");

        }

        Usuario usuario = userRepo.findByUsername(username);
        if(usuario==null){
            Alertas.alertar(TipoAlerta.ERROR,"Error de usuario","Usuario no encontrado");
            throw new Exception("Usuario no encontrado");
        }

        if(!usuario.getPassword().equals(password)){
            Alertas.alertar(TipoAlerta.ERROR,"Error de contraseña","Contraseña incorrecta");
            throw new Exception("Contraseña incorrecta");

        }


        UsuarioLoggeado.getInstance().setRut(usuario.getRut());

        return usuario.getTipo().toString();
    }
}
