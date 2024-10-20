package co.edu.uniquindio.ingsoftwareproject.repository.interfaces;

import co.edu.uniquindio.ingsoftwareproject.model.classes.Notificacion;

import java.util.List;

public interface NotificacionRepo {

    public String save(Notificacion notificacion);
    public List<Notificacion> findAll();
    public Notificacion findById(String id);


}
