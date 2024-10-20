package co.edu.uniquindio.ingsoftwareproject.user;

import lombok.*;

@Setter
@Getter
public class UsuarioLoggeado {

    private String rut;
    public static UsuarioLoggeado instance;

    private UsuarioLoggeado (){

    }
    public static UsuarioLoggeado getInstance (){

        if (instance == null){
            instance = new UsuarioLoggeado();
        }

        return instance;

    }

}
