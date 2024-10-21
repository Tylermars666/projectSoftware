package co.edu.uniquindio.ingsoftwareproject.repository.implement;

import co.edu.uniquindio.ingsoftwareproject.model.classes.Notificacion;
import co.edu.uniquindio.ingsoftwareproject.model.classes.Usuario;
import co.edu.uniquindio.ingsoftwareproject.repository.Singleton;
import co.edu.uniquindio.ingsoftwareproject.repository.interfaces.NotificacionRepo;

import java.util.ArrayList;
import java.util.List;

public class NotificacionRepoImpl implements NotificacionRepo {

    Singleton singleton = Singleton.getInstance();

    @Override
    public String save(Notificacion notificacion) {

        Notificacion notificacionDB = findById(notificacion.getId());
        if(notificacionDB != null) {

            singleton.eliminar("notificacion", notificacionDB);
            singleton.crear("notificacion", notificacion);

            return "Se ha sobreescrito la notificacion con id: "+ notificacion.getId();

        }else{

            singleton.crear("notificacion", notificacion);

        }



        return "notificacion guardada con éxito";
    }

    @Override
    public List<Notificacion> findAll() {
        return (ArrayList<Notificacion>) singleton.listar("notificacion");
    }

    @Override
    public Notificacion findById(String id) {

        ArrayList<Notificacion> notificaciones = (ArrayList<Notificacion>) singleton.listar("notificacion");

        Notificacion notificacion = null;

        for(Notificacion notificacion1 : notificaciones) {

            if (notificacion1.getId().equals(id)) {

                notificacion = notificacion1;
                break;

            }

        }

        return notificacion;
    }
}
