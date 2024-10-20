package co.edu.uniquindio.ingsoftwareproject.repository.interfaces;

import co.edu.uniquindio.ingsoftwareproject.model.classes.Usuario;

import java.util.List;

public interface UserRepo {

    public List<Usuario> findAll();
    public Usuario findByRut(String rut);
    public String save(Usuario usuario); //Este es para actualizar también
    public Usuario findByUsername(String username);

}
