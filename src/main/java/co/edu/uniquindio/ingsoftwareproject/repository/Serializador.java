package co.edu.uniquindio.ingsoftwareproject.repository;

import co.edu.uniquindio.ingsoftwareproject.model.classes.Notificacion;
import co.edu.uniquindio.ingsoftwareproject.model.classes.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Serializador {


    public Object serializarUsuario (Usuario usuario) {

        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) deserializarUsuarios();

        if(usuarios==null){
            usuarios = new ArrayList<>();
        }

        usuarios.add(usuario);

        try{

            ObjectOutputStream listaPersistida = new ObjectOutputStream(new FileOutputStream(
                    "src/main/java/co/edu/uniquindio/ingsoftwareproject/database/usuarios/listaUsuarios"));
            listaPersistida.writeObject(usuarios);
            listaPersistida.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

        return usuario;
    }

    public Object deserializarUsuarios () {

        try{

            ObjectInputStream listaObtenida = new ObjectInputStream(new FileInputStream(
                    "src/main/java/co/edu/uniquindio/ingsoftwareproject/database/usuarios/listaUsuarios"));
            ArrayList<Usuario> listaLeida = (ArrayList<Usuario>) listaObtenida.readObject();
            return listaLeida;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    public Object serializarNotificacion (Notificacion notificacion) {
        ArrayList<Notificacion> notificaciones = (ArrayList<Notificacion>) deserializarNotificaciones();

        if(notificaciones==null){
            notificaciones = new ArrayList<>();
        }

        notificaciones.add(notificacion);

        try{

            ObjectOutputStream listaPersistida = new ObjectOutputStream(new FileOutputStream(
                    "src/main/java/co/edu/uniquindio/ingsoftwareproject/database/notificaciones/listaNotificaciones"));
            listaPersistida.writeObject(notificaciones);
            listaPersistida.flush();
        }catch (Exception e){
            e.printStackTrace();
        }

        return notificacion;
    }

    public Object deserializarNotificaciones () {


        try{
            ObjectInputStream listaObtenida = new ObjectInputStream(new FileInputStream(
                    "src/main/java/co/edu/uniquindio/ingsoftwareproject/database/notificaciones/listaNotificaciones"));
            ArrayList<Notificacion> listaLeida = (ArrayList<Notificacion>) listaObtenida.readObject();
            return listaLeida;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public Object eliminarUsuario(Usuario usuario) {

        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) deserializarUsuarios();

        for (Usuario usuario1 : usuarios) {

            if(usuario1.getRut().equals(usuario.getRut())){

                usuarios.remove(usuario1);
                break;
            }

        }

        try{

            ObjectOutputStream listaPersistida = new ObjectOutputStream(new FileOutputStream(
                    "src/main/java/co/edu/uniquindio/ingsoftwareproject/database/usuarios/listaUsuarios"));
            listaPersistida.writeObject(usuarios);
            listaPersistida.flush();
            return usuario;

        }catch (Exception e){
            e.printStackTrace();
        }

        return usuario;



    }

    public Object eliminarNotificacion(Notificacion notificacion){

        ArrayList<Notificacion> notificaciones = (ArrayList<Notificacion>) deserializarNotificaciones();

        for (Notificacion notificacion1 : notificaciones) {

            if(notificacion1.getId().equals(notificacion.getId())){

                notificaciones.remove(notificacion);
                break;
            }

        }

        try{

            ObjectOutputStream listaPersistida = new ObjectOutputStream(new FileOutputStream(
                    "src/main/java/co/edu/uniquindio/ingsoftwareproject/database/notificaciones/listaNotificaciones"));
            listaPersistida.writeObject(notificaciones);
            listaPersistida.flush();
            return notificacion;

        }catch (Exception e){
            e.printStackTrace();
        }

        return notificacion;

    }




}
