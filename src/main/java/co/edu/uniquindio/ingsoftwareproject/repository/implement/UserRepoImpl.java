package co.edu.uniquindio.ingsoftwareproject.repository.implement;

import co.edu.uniquindio.ingsoftwareproject.model.classes.Usuario;
import co.edu.uniquindio.ingsoftwareproject.repository.Singleton;
import co.edu.uniquindio.ingsoftwareproject.repository.interfaces.UserRepo;

import java.util.ArrayList;
import java.util.List;

public class UserRepoImpl implements UserRepo {

    Singleton singleton = Singleton.getInstance();

    @Override
    public List<Usuario> findAll() {
        return (ArrayList<Usuario>) singleton.listar("user");
    }

    @Override
    public List<Usuario> findAllUsersNotAdmin() {

        ArrayList<Usuario> users = (ArrayList<Usuario>) singleton.listar("user");

        return filtrarUsuariosNoAdmin(users);
    }

    @Override
    public Usuario findByRut(String rut) {

        List<Usuario> usuariosCompletos = (List<Usuario>) singleton.listar("user");
        Usuario usuario = null;

        for(Usuario usuarioBuscado : usuariosCompletos) {

            if(usuarioBuscado.getRut().equals(rut)) {
                usuario = usuarioBuscado;
                break;
            }

        }


        return usuario;
    }

    @Override
    public String save(Usuario usuario) {

        Usuario usuarioDB = findByRut(usuario.getRut());
        if(usuarioDB != null) {

            singleton.eliminar("user", usuarioDB);
            singleton.crear("user", usuario);

            return "Se ha sobreescrito el usuario con rut: "+ usuario.getRut();

        }else{

            singleton.crear("user", usuario);

        }



        return "usuario guardado con éxito";
    }

    @Override
    public Usuario findByUsername(String username) {

        List<Usuario> usuariosCompletos = (List<Usuario>) singleton.listar("user");

        for (Usuario usuarioBuscado : usuariosCompletos) {

            if (usuarioBuscado.getUserName().equals(username)) {
                return usuarioBuscado;
            }

        }


        return null;
    }

    //----------------------------METODO AUX---------------------

    private List<Usuario> filtrarUsuariosNoAdmin(List<Usuario> usuarios) {

        ArrayList<Usuario> usuariosFiltrados = new ArrayList<>();

        for(Usuario usuario : usuarios) {

            if(usuario.getTipo().toString().equals("CLIENTE")) {

                usuariosFiltrados.add(usuario);

            }
        }
        return usuariosFiltrados;

    }
}
