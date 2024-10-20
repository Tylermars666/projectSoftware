package co.edu.uniquindio.ingsoftwareproject.services.implement;

import co.edu.uniquindio.ingsoftwareproject.model.classes.Usuario;
import co.edu.uniquindio.ingsoftwareproject.repository.implement.UserRepoImpl;
import co.edu.uniquindio.ingsoftwareproject.repository.interfaces.UserRepo;
import co.edu.uniquindio.ingsoftwareproject.services.interfaces.LoginService;
import co.edu.uniquindio.ingsoftwareproject.user.UsuarioLoggeado;

public class LoginServiceImpl implements LoginService {

    UserRepo userRepo = new UserRepoImpl();


    @Override
    public String login(String username, String password) throws Exception {

        Usuario usuario = userRepo.findByUsername(username);
        if(usuario==null){
            throw new Exception("Usuario no encontrado");
        }

        if(!usuario.getPassword().equals(password)){
            throw new Exception("Contraseña incorrecta");

        }


        UsuarioLoggeado.getInstance().setRut(usuario.getRut());

        return "Usuario loggeado con exito";
    }
}
