package co.edu.uniquindio.ingsoftwareproject.repository;


import co.edu.uniquindio.ingsoftwareproject.model.classes.Notificacion;
import co.edu.uniquindio.ingsoftwareproject.model.classes.Usuario;
import lombok.Getter;

@Getter
public class Singleton {

    private Serializador serializador;

    private static Singleton instance;
    private Singleton() {
        serializador = new Serializador();
    }

    public static Singleton getInstance() {
        if(instance==null){
            instance = new Singleton();
        }
        return instance;
    }

    public Object crear(String entidad, Object objeto) {

        Object response = null;

        switch (entidad){

            case "user":
                response = serializador.serializarUsuario((Usuario) objeto);
                break;

            case "notificacion":
                response = serializador.serializarNotificacion((Notificacion) objeto);
                break;


        }

        return response;

    }

    public Object listar(String entidad) {

        Object response = null;

        switch (entidad){

            case "user":
                response = serializador.deserializarUsuarios();
                break;

            case "notificacion":
                response = serializador.deserializarNotificaciones();
                break;


        }

        return response;

    }

    public Object eliminar(String entidad, Object objeto) {

        Object response = null;

        switch (entidad){

            case "user":
                response = serializador.eliminarUsuario((Usuario) objeto);
                break;

            case "notificacion":
                response = serializador.eliminarNotificacion((Notificacion) objeto);
                break;


        }

        return response;
    }





}
